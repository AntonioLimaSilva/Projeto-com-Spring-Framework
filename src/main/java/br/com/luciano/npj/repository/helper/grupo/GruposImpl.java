package br.com.luciano.npj.repository.helper.grupo;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.Grupo;

public class GruposImpl implements GruposQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Grupo> buscarPorId(Integer id) {
		String query = "select distinct g from Grupo g inner join fetch g.permissoes p where g.id = :id";
		return this.manager.createQuery(query, Grupo.class)
				.setParameter("id", id)
				.getResultList().stream().findFirst();
	}

}
