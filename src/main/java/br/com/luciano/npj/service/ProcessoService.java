package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Processo;
import br.com.luciano.npj.repository.Processos;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class ProcessoService {

	@Autowired
	private Processos processos;
	
	@Transactional
	public Processo salvar(Processo processo) {
		if(processo.isNovo()) {
			processo.setDataCriacao(LocalDateTime.now());
		} else {
			Processo processoExistente = this.processos.findOne(processo.getId());
			processo.setDataCriacao(processoExistente.getDataCriacao());
		}
		
		if(processo.getItensAssistido().isEmpty()) {
			throw new NegocioException("Informe pelo menos um assistido no processo");
		}
		
		try {
			return this.processos.saveAndFlush(processo);
		} catch(PersistentObjectException e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.processos.delete(id);
			this.processos.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro tentanto excluir processo");
		}		
	}
	
}
