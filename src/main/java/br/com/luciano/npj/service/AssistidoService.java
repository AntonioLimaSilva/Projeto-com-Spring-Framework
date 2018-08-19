package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.repository.Assistidos;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class AssistidoService {
	
	@Autowired
	private Assistidos assistidos;
	
	@Transactional
	public void salvar(Assistido assistido) {
		if(assistido.isNovo()) {
			assistido.setDataCriacao(LocalDateTime.now());
		} else {
			Assistido assistidoExistente = this.assistidos.findOne(assistido.getId());
			assistido.setDataCriacao(assistidoExistente.getDataCriacao());
		}
		
		this.assistidos.save(assistido);
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.assistidos.delete(id);
			this.assistidos.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo assistido");
		}
	}
}
