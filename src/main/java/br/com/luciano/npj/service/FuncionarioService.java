package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.repository.Funcionarios;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class FuncionarioService {
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Transactional
	public void salvar(Funcionario funcionario) {
		if(funcionario.isNovo()) {
			funcionario.setDataCriacao(LocalDateTime.now());
		} else {
			Funcionario funcionarioExistente = this.funcionarios.findOne(funcionario.getId());
			funcionario.setDataCriacao(funcionarioExistente.getDataCriacao());
		}
		
		this.funcionarios.save(funcionario);
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.funcionarios.delete(id);
			this.funcionarios.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo funcionário");
		}
	}

}
