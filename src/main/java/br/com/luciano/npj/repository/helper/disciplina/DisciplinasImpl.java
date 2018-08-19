package br.com.luciano.npj.repository.helper.disciplina;

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

import br.com.luciano.npj.model.Disciplina;
import br.com.luciano.npj.repository.filter.DisciplinaFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class DisciplinasImpl implements DisciplinasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Disciplina> filtrar(Pageable pageable, DisciplinaFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Disciplina.class);
		adicionarFiltro(criteria, filtro);
		this.paginacaoUtil.preparar(criteria, pageable);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private void adicionarFiltro(Criteria criteria, DisciplinaFilter filtro) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}
	
	private Long total(DisciplinaFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Disciplina.class);
		criteria.setProjection(Projections.rowCount());
		adicionarFiltro(criteria, filtro);
		return (Long) criteria.uniqueResult();
	}

}
