package br.com.luciano.npj.repository.helper.cargas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.dto.CargaDTO;
import br.com.luciano.npj.model.Carga;
import br.com.luciano.npj.repository.filter.CargaFilter;

public class CargasImpl implements CargasQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<CargaDTO> filtrar(Pageable pageable, CargaFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CargaDTO> criteria = builder.createQuery(CargaDTO.class);
		Root<Carga> root = criteria.from(Carga.class);
				
		criteria.select(builder.construct(CargaDTO.class
				, root.get("id")
				, root.get("processo").get("id")
				, root.get("pessoa").get("nome")
				, root.get("usuario").get("login")
				, root.get("dataSaida")
				, root.get("dataEntrega")));
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);	
		criteria.orderBy(builder.desc(root.get("id")));
		
		TypedQuery<CargaDTO> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filtro));
	}
	
	@Override
	public Optional<Carga> buscarPorId(Integer id) {
		String query = "select distinct c from Carga c inner join fetch c.pessoa p1 inner join fetch c.processo p2 where c.id = :id";
		return this.manager.createQuery(query, Carga.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(CargaFilter cargaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Carga> root = criteria.from(Carga.class);
		
		Predicate[] predicates = criarRestricoes(cargaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(CargaFilter cargaFilter, CriteriaBuilder builder,
			Root<Carga> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		Expression<String> expression = root.get("pessoa").get("nome");
		
		if (!StringUtils.isEmpty(cargaFilter.getNomePessoa())) {
			predicates.add(builder.like(builder.lower(expression), "%" + cargaFilter.getNomePessoa().toLowerCase() + "%"));
		}	
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
