package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.repository.Reus;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class ReuService {
	
	@Autowired
	private Reus reus;
	
	@Transactional
	public void salvar(Reu reu) {
		if(reu.isNovo()) {
			reu.setDataCriacao(LocalDateTime.now());
		} else {
			Reu reuExistente = reus.findOne(reu.getId());
			reu.setDataCriacao(reuExistente.getDataCriacao());
		}
		
		this.reus.save(reu);
	}
	
	@Transactional
	public void excluir(Integer id) {
		try {
			this.reus.delete(id);
			this.reus.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro exluíndo réu");
		}
	}

}
