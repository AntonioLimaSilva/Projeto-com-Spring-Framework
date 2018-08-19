package br.com.luciano.npj.repository.helper.acao.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.AcaoProcesso;
import br.com.luciano.npj.model.Processo;

public class AcoesProcessosImpl implements AcoesProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AcaoProcesso> buscarAcoesPorProcesso(Processo processo) {
		String query = "select ac from AcaoProcesso ac inner join fetch ac.acao a "
				+ "inner join ac.processo p where p = :processo";
		return this.manager.createQuery(query, AcaoProcesso.class)
				.setParameter("processo", processo)
				.getResultList();
	}

}
