package br.com.luciano.npj.repository.helper.documento.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.DocumentoProcesso;
import br.com.luciano.npj.model.Processo;

public class DocumentosProcessosImpl implements DocumentosProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<DocumentoProcesso> buscarDocumentosPorProcesso(Processo processo) {
		String query = "select dp from DocumentoProcesso dp inner join fetch dp.documento d "
				+ "inner join dp.processo p where p = :processo";
		return this.manager.createQuery(query, DocumentoProcesso.class)
				.setParameter("processo", processo)
				.getResultList();
	}

}
