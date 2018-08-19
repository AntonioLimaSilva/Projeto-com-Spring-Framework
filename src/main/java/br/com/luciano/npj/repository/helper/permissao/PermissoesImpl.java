package br.com.luciano.npj.repository.helper.permissao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.model.Permissao;

public class PermissoesImpl implements PermissoesQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> buscarPermissoesPorGrupo(Integer idGrupo) {
		String query = "select p from Permissao p inner join GrupoPermissao gp on gp.id.permissao.id = p.id "
				+ "inner join Grupo g on g.id = gp.id.grupo.id where g.id = :idGrupo";
		return this.manager.createQuery(query, Permissao.class)
				.setParameter("idGrupo", idGrupo)
				.getResultList();
	}

}
