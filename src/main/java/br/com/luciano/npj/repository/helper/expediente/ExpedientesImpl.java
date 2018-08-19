package br.com.luciano.npj.repository.helper.expediente;

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

import br.com.luciano.npj.model.Expediente;
import br.com.luciano.npj.repository.filter.ExpedienteFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class ExpedientesImpl implements ExpedientesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Expediente> filtrar(Pageable pageable, ExpedienteFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Expediente.class);
		adicionarFiltro(criteria, filtro);
		this.paginacaoUtil.preparar(criteria, pageable);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public Optional<Expediente> buscarPor(Integer id) {
		String query = "select distinct e from Expediente e inner join fetch e.funcionario f inner join fetch f.pessoa p "
				+ "inner join fetch e.disciplina d inner join fetch e.turmas t where e.id = :id";
		
		return this.manager.createQuery(query, Expediente.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}
	
	@Override
	public List<Expediente> buscarExpedientes() {
		String query = "select distinct e from Expediente e inner join fetch e.funcionario f inner join fetch f.pessoa p inner join fetch e.turmas";
		return this.manager.createQuery(query, Expediente.class).getResultList();
	}

	private void adicionarFiltro(Criteria criteria, ExpedienteFilter filtro) {
		criteria.createAlias("funcionario", "f", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("f.pessoa","p", JoinType.LEFT_OUTER_JOIN);
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNomeFuncionario())) {
				criteria.add(Restrictions.ilike("p.nome", filtro.getNomeFuncionario(), MatchMode.ANYWHERE));
			}
		}
	}

	private Long total(ExpedienteFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Expediente.class);
		criteria.setProjection(Projections.rowCount());
		adicionarFiltro(criteria, filtro);
		return (Long) criteria.uniqueResult();
	}



}