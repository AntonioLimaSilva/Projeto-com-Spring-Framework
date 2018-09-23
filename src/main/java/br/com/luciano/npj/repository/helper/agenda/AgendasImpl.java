package br.com.luciano.npj.repository.helper.agenda;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.dto.AgendaDTO;

public class AgendasImpl implements AgendasQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AgendaDTO> buscarTodas() {
		String query = "select new br.com.luciano.npj.dto.AgendaDTO(assistido, dataInicio, dataFim, color) from Agenda where dataInicio >= NOW()";
		return this.manager.createQuery(query, AgendaDTO.class).getResultList();
	}

}
