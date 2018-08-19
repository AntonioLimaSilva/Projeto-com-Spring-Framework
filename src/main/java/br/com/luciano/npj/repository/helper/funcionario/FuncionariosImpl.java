package br.com.luciano.npj.repository.helper.funcionario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.dto.FuncionarioDTO;
import br.com.luciano.npj.dto.FuncionarioMesDTO;
import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.repository.filter.FuncionarioFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class FuncionariosImpl implements FuncionariosQueries {
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Funcionario> filtrar(FuncionarioFilter filtro, Pageable pageable) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Funcionario.class);
		
		this.paginacaoUtil.preparar(criteria, pageable);
		this.adicionarFiltro(filtro, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(FuncionarioFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Funcionario.class);
		this.adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	

	@Override
	public List<FuncionarioDTO> buscarFuncionarioQueNaoEstaoExpediente(String nome) {
		String query = "select new br.com.luciano.npj.dto.FuncionarioDTO(f.id, p.nome, p.pseudonimo, p.cpf) from Funcionario f "
				+ "left join Pessoa p on p.id = f.pessoa.id where lower(p.nome) like lower(:nome) "
				+ "and f.id not in(select e.funcionario.id from Expediente e)";
			
		return this.manager.createQuery(query, FuncionarioDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

	@Override
	public List<FuncionarioDTO> buscarPessoaFuncionarioPorNome(String nome) {
		String query = "select new br.com.luciano.npj.dto.FuncionarioDTO(f.id, p.nome, p.pseudonimo, p.cpf) from Funcionario f "
				+ "left join Pessoa p on p.id = f.pessoa.id where lower(p.nome) like lower(:nome)";
		return this.manager.createQuery(query, FuncionarioDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public Optional<Funcionario> buscarPorId(Integer id) {
		String query = "select distinct f from Funcionario f inner join fetch f.pessoa p where f.id = :id";
		return this.manager.createQuery(query, Funcionario.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	} 
	
	@Transactional(readOnly = true)
	@Override
	public Funcionario buscarFuncionarioComPessoaPorId(Integer idFuncionario) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Funcionario.class);
		criteria.createAlias("pessoa", "p", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", idFuncionario));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Funcionario) criteria.uniqueResult();
	}

	@Override
	public List<FuncionarioMesDTO> buscarTotalMes() {
		List<FuncionarioMesDTO> resultList = this.manager.createNamedQuery("Funcionarios.totalPorMes", FuncionarioMesDTO.class).getResultList();
		
		LocalDate now = LocalDate.now();
		for (int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", now.getYear(), now.getMonth().getValue());
			
			boolean possuiMes = resultList.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if (!possuiMes) {
				resultList.add(i - 1, new FuncionarioMesDTO(mesIdeal, 0));
			}
			
			now = now.minusMonths(1);
		}
		
		return resultList;
	}

	private void adicionarFiltro(FuncionarioFilter filtro, Criteria criteria) {
		criteria.setFetchMode("pessoa", FetchMode.JOIN);
		criteria.createAlias("pessoa", "p");
		
		if(filtro != null) {
			if(filtro.getPessoa() != null && !StringUtils.isEmpty(filtro.getPessoa().getNome())) {
				criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getFuncao())) {
				criteria.add(Restrictions.ilike("funcao", filtro.getFuncao(), MatchMode.ANYWHERE));
			}
		}
	}

}
