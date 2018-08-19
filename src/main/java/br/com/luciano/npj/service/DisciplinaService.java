package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Disciplina;
import br.com.luciano.npj.repository.Disciplinas;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class DisciplinaService {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Transactional
	public void salvar(Disciplina disciplina) {
		
		if (disciplina.isNova()) {
			disciplina.setDataCriacao(LocalDateTime.now());
		} else {
			Disciplina disciplinaSalva = this.disciplinas.findOne(disciplina.getId());
			disciplina.setDataCriacao(disciplinaSalva.getDataCriacao());
		}
		
		this.disciplinas.save(disciplina);
	}

	@Transactional
	public void exluir(Integer id) {
		try {
			this.disciplinas.delete(id);
			this.disciplinas.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Erro tentando excluir disciplina");
		}	
	}

}
