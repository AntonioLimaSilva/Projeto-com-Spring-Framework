package br.com.luciano.npj.repository.paginacao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {
	
	public void preparar(Criteria criteria, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		Sort ordenar = pageable.getSort();
		if(ordenar != null) {
			Sort.Order ordem = ordenar.iterator().next();
			String propriedade = ordem.getProperty();
			criteria.addOrder(ordem.isAscending() ? Order.asc(propriedade) : Order.desc(propriedade));
		}
	}
	

}
