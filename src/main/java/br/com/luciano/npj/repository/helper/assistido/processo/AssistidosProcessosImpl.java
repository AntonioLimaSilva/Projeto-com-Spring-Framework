package br.com.luciano.npj.repository.helper.assistido.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.model.Processo;

public class AssistidosProcessosImpl implements AssistidosProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AssistidoProcesso> buscarAssistidosPorProcesso(Processo processo) {
		String query = "select ap from AssistidoProcesso ap inner join fetch ap.assistido a "
				+ "inner join fetch a.pessoa p1 inner join ap.processo p2 where p2 = :processo";
		return this.manager.createQuery(query, AssistidoProcesso.class)
				.setParameter("processo", processo)
				.getResultList();
	}

}
