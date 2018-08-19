package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Expediente;
import br.com.luciano.npj.repository.Expedientes;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class ExpedienteService {
	
	@Autowired
	private Expedientes expedientes;
	
	@Transactional
	public void salvar(Expediente expediente) {
		if(expediente.isNovo()) {
			expediente.setDataCriacao(LocalDateTime.now());
		} else {
			Expediente expedienteSalvo = this.expedientes.findOne(expediente.getId());
			expediente.setDataCriacao(expedienteSalvo.getDataCriacao());
		}
		
		this.expedientes.save(expediente);
	}
	
	@Transactional
	public void excluir(Integer id) {
		try {
			this.expedientes.delete(id);
			this.expedientes.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo expediente");
		}
	}

}
