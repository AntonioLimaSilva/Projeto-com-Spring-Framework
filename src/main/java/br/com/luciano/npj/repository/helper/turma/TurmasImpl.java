package br.com.luciano.npj.repository.helper.turma;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.luciano.npj.model.Turma;

public class TurmasImpl implements TurmasQueries {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Turma> buscarTurmas() {
		String query = "select distinct t from Turma t left join fetch t.horarios h";	
		return this.manager.createQuery(query, Turma.class).getResultList();
	}

	@Override
	public Optional<Turma> buscarPorId(Integer id) {
		String query = "select t from Turma t inner join fetch t.horarios h where t.id = :id";
		return this.manager.createQuery(query, Turma.class)
				.setParameter("id", id)
				.getResultList()
				.stream().findFirst();
	}

}
