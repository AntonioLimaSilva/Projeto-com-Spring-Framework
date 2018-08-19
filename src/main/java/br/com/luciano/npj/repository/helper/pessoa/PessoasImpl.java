package br.com.luciano.npj.repository.helper.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.dto.PessoaDTO;
import br.com.luciano.npj.dto.PessoaProcessoDTO;
import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.model.Pessoa;
import br.com.luciano.npj.repository.Alunos;
import br.com.luciano.npj.repository.filter.PessoaFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;
import br.com.luciano.npj.security.UsuarioSistema;

public class PessoasImpl implements PessoasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private Alunos alunos;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Pessoa> filtrar(PessoaFilter filtro, Pageable pageable, UsuarioSistema usuario) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Pessoa.class);
		
		List<Pessoa> lista = new ArrayList<>();
		Optional<Aluno> alunoOptional = alunos.buscarAlunoPorId(usuario.getUsuario().getPessoa().getId());
		
		this.adicionarFiltro(filtro, criteria);
		
		this.paginacaoUtil.preparar(criteria, pageable);
		
		if(alunoOptional.isPresent()) {
			lista.add(alunoOptional.get().getPessoa());
		} else {			
			lista = criteria.list();		
		}		
				
		return new PageImpl<>(lista, pageable, lista.size() > 1 ? total(filtro) : 1L);
	}

	/**
	 * Busca todos as pessoas que não estão presente na tabela usuário
	 */
	@Transactional(readOnly = true)
	@Override
	public List<PessoaDTO> buscarTodosQueNaoEstaoEmUsuario(String nome) {
		return this.manager.createQuery("select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where upper(p.nome) like upper(:nome) "
				+ "and p.id not in(select u.pessoa.id from Usuario u)", PessoaDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public List<PessoaDTO> buscarTodosQueNaoEstaoEmAssistido(String nome) {
		return this.manager.createQuery("select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where upper(p.nome) like upper(:nome) "
				+ "and p.id not in(select a.pessoa.id from Assistido a)", PessoaDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public List<PessoaDTO> buscarTodosQueNaoEstaoEmFuncionario(String nome) {
		return this.manager.createQuery("select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where upper(p.nome) like upper(:nome) "
				+ "and p.id not in(select f.pessoa.id from Funcionario f)", PessoaDTO.class)
				.setParameter("nome", "%"+ nome + "%")
				.getResultList();
	}
	
	@Override
	public List<PessoaDTO> buscarTodosQueNaoEstaoEmReu(String nome) {
		return this.manager.createQuery("select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where lower(p.nome) like lower(:nome) "
				+ "and p.id not in(select r.pessoa.id from Reu r)", PessoaDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public List<PessoaDTO> buscarTodosQueNaoEstaoEmAluno(String nome) {
		return this.manager.createQuery("select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where lower(p.nome) like lower(:nome) "
				+ "and p.id not in(select a.pessoa.id from Aluno a)", PessoaDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	@Override
	public List<PessoaDTO> buscarPessoasPorNome(String nome) {
		String query = "select new br.com.luciano.npj.dto.PessoaDTO(p.id, p.nome, p.pseudonimo, p.cpf) from Pessoa p "
				+ "where lower(p.nome) like lower(:nome) ";
		return this.manager.createQuery(query, PessoaDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public Optional<Pessoa> buscarPorId(Integer id) {
		String query = "select distinct p from Pessoa p inner join fetch p.endereco e1 inner join fetch e1.cidade c inner join fetch c.estado e2 where p.id = :id";
		return this.manager.createQuery(query, Pessoa.class).setParameter("id", id).getResultList().stream().findAny();
	}
	
	@Override
	public List<PessoaProcessoDTO> buscarPessoasComProcesso(String nome) {
		String query = "select new br.com.luciano.npj.dto.PessoaProcessoDTO(pessoa.nome, pessoa.cpf, processo.id as idProcesso, processo.localizacao) "
				+ "from Pessoa pessoa inner join Assistido assistido on assistido.pessoa.id = pessoa.id "
				+ "inner join AssistidoProcesso assitidoProcesso on assitidoProcesso.assistido.id = assistido.id "
				+ "inner join Processo processo on processo.id = assitidoProcesso.processo.id "
				+ "where lower(pessoa.nome) like lower(:nome)";
		return this.manager.createQuery(query, PessoaProcessoDTO.class)
			.setParameter("nome", "%" + nome + "%")
			.getResultList();
	}
	
	private Long total(PessoaFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Pessoa.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(PessoaFilter filtro, Criteria criteria) {
		if(filtro != null) {			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getPseudonimo())) {
				criteria.add(Restrictions.ilike("pseudonimo", filtro.getPseudonimo(), MatchMode.START));
			}
			
			if(!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
			}
		}
	}

}
