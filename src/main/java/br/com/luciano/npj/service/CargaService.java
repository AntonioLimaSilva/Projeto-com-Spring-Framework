package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Carga;
import br.com.luciano.npj.repository.Cargas;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class CargaService {
	
	@Autowired
	private Cargas cargas;
	
	@Transactional
	public void salvar(Carga carga) {
		if(carga.isNova()) {
			carga.setDataCriacao(LocalDateTime.now());
		} else {
			Carga cargaExistente = this.cargas.findOne(carga.getId());
			carga.setUsuario(cargaExistente.getUsuario());
			carga.setDataCriacao(cargaExistente.getDataCriacao());
		}
		
		this.cargas.save(carga);
	}
	
	@Transactional
	public void excluir(Integer id) {
		try {
			this.cargas.delete(id);
			this.cargas.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro tentando excluir carga");
		}
	}

}
