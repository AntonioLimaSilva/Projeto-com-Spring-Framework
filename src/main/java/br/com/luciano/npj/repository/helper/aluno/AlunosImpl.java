package br.com.luciano.npj.repository.helper.aluno;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
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

import br.com.luciano.npj.dto.AlunoDTO;
import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.repository.filter.AlunoFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class AlunosImpl implements AlunosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	public List<AlunoDTO> buscarPessoaAlunoPorNome(String nome) {
		String query = "select new br.com.luciano.npj.dto.AlunoDTO(a.id, p.nome, p.pseudonimo, p.cpf) from Aluno a "
				+ "left join Pessoa p on p.id = a.pessoa.id where lower(p.nome) like lower(:nome)";
		
		return this.manager.createQuery(query, AlunoDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

	@Override
	public Optional<Aluno> buscarPorId(Integer id) {
		String query = "select distinct a from Aluno a inner join fetch a.pessoa p "
				+ "inner join fetch a.cabine c inner join fetch a.equipe e inner join fetch a.expediente exp "
				+ "inner join fetch exp.funcionario f inner join fetch f.pessoa p1 inner join fetch exp.turmas t where a.id = :id";
		return this.manager.createQuery(query, Aluno.class)
				.setParameter("id", id)
				.getResultList().stream().findFirst();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Aluno> filtrar(Pageable pageable, AlunoFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Aluno.class);
		this.paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(criteria, filtro);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public Optional<Aluno> buscarAlunoPorId(Integer idUsuario) {
		String query = "select a from Aluno a inner join fetch a.pessoa p where p.id = :idUsuario";
		return this.manager.createQuery(query, Aluno.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList()
				.stream().findFirst();
	}
	
	private void adicionarFiltro(Criteria criteria, AlunoFilter filtro) {
		criteria.createAlias("pessoa", "p", JoinType.LEFT_OUTER_JOIN);
		if(filtro != null) {
			if(filtro.getPessoa() != null && !StringUtils.isEmpty(filtro.getPessoa().getNome())) {
				criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(AlunoFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Aluno.class);
		criteria.setProjection(Projections.rowCount());
		adicionarFiltro(criteria, filtro);
		return (Long) criteria.uniqueResult();
	}


}