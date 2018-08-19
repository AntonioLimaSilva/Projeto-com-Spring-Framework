package br.com.luciano.npj.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Turma;
import br.com.luciano.npj.repository.Turmas;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class TurmaService {
	
	@Autowired
	private Turmas turmas;
	
	@Transactional
	public void salvar(Turma turma) {
		this.turmas.save(turma);
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.turmas.delete(id);
			this.turmas.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo turma");
		}
	}
}
