package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.repository.Alunos;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class AlunoService {
	
	@Autowired
	private Alunos alunos;
	
	@Transactional
	public void salvar(Aluno aluno) {
		if(aluno.isNovo()) {
			aluno.setDataCriacao(LocalDateTime.now());
		} else {
			Aluno alunoExistente = this.alunos.findOne(aluno.getId());
			aluno.setDataCriacao(alunoExistente.getDataCriacao());
		}
		
		this.alunos.save(aluno);
	}

	@Transactional
	public void excluir(Integer id) {
		try{
			this.alunos.delete(id);
			this.alunos.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo aluno");
		}
	}

}
