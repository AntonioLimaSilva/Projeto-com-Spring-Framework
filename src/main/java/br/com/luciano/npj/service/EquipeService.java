package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Equipe;
import br.com.luciano.npj.repository.Equipes;

@Service
public class EquipeService {
	
	@Autowired
	private Equipes equipes;
	
	@Transactional
	public void salvar(Equipe equipe) {
		if (equipe.isNova()) {
			equipe.setDataCriacao(LocalDateTime.now());
		} else {
			Equipe equipeExistente = equipes.findOne(equipe.getId());
			equipe.setDataCriacao(equipeExistente.getDataCriacao());
		}
		
		this.equipes.save(equipe);
	}

}
