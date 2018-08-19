package br.com.luciano.npj.repository.helper.reu;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.repository.filter.ReuFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class ReusImpl implements ReusQueries {
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public Page<Reu> filtrar(ReuFilter filtro, Pageable pageable) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Reu.class);
		
		this.paginacaoUtil.preparar(criteria, pageable);
		this.adicionarFiltro(criteria, filtro);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public Optional<Reu> buscarPorId(Integer id) {
		String query = "select distinct r from Reu r inner join fetch r.pessoa p where r.id = :id";
		return this.manager.createQuery(query, Reu.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}

	private Long total(ReuFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Reu.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	public void adicionarFiltro(Criteria criteria, ReuFilter filtro) {
		criteria.setFetchMode("pessoa", FetchMode.JOIN);
		criteria.createAlias("pessoa", "p");
		
		if(filtro != null) {
			if(filtro.getPessoa() != null && !StringUtils.isEmpty(filtro.getPessoa().getNome())) {
				criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
			}
		}
	}
	
}
