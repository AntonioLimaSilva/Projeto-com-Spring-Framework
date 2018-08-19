package br.com.luciano.npj.repository.helper.aluno.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.AlunoProcesso;
import br.com.luciano.npj.model.Processo;

public class AlunosProcessosImpl implements AlunosProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AlunoProcesso> buscarAlunosPorProcesso(Processo processo) {
		String query = "select ap from AlunoProcesso ap inner join fetch ap.aluno a "
				+ "inner join fetch a.pessoa p1 inner join ap.processo p2 where p2 = :processo";
		
		return this.manager.createQuery(query, AlunoProcesso.class)
				.setParameter("processo", processo)
				.getResultList();
	}

}
