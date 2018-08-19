package br.com.luciano.npj.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Grupo;
import br.com.luciano.npj.repository.Grupos;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class GrupoService {
	
	@Autowired
	private Grupos grupos;
	
	@Transactional
	public void salvar(Grupo grupo) {
		this.grupos.save(grupo);
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.grupos.delete(id);
			this.grupos.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo grupo");
		}	
	}

}
