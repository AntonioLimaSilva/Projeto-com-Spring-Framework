package br.com.luciano.npj.repository.helper.funcionario.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.model.Processo;

public class FuncionariosProcessosImpl implements FuncionariosProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FuncionarioProcesso> buscarFuncionariosPorProcesso(Processo processo) {
		String query = "select fp from FuncionarioProcesso fp inner join fetch fp.funcionario f "
				+ "inner join fetch f.pessoa p1 inner join fp.processo p2 where p2 = :processo";
		
		return this.manager.createQuery(query, FuncionarioProcesso.class)
				.setParameter("processo", processo)
				.getResultList();
	}

}
