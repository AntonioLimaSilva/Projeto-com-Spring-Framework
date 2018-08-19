package br.com.luciano.npj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Agenda;
import br.com.luciano.npj.repository.Agendas;

@Service
public class AgendaService {
	
	@Autowired
	private Agendas agendas;
	
	@Transactional
	public void salvar(Agenda agenda) {
		this.agendas.save(agenda);
	}

}
