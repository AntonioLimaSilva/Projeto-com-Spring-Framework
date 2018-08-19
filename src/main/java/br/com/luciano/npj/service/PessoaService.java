package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Pessoa;
import br.com.luciano.npj.repository.Pessoas;
import br.com.luciano.npj.service.event.pessoa.PessoaEvent;
import br.com.luciano.npj.service.exception.CpfJaCadastradoException;
import br.com.luciano.npj.service.exception.NegocioException;
import br.com.luciano.npj.storage.FotoStorage;

@Service
public class PessoaService {
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		
		if(!StringUtils.isEmpty(pessoa.getCpf())) {
			Pessoa pessoaComCpfJaCadatrado = this.pessoas.findByCpf(pessoa.getCpf());
			
			if(pessoaComCpfJaCadatrado != null && !pessoaComCpfJaCadatrado.equals(pessoa)) {
				throw new CpfJaCadastradoException("O cpf já esta cadastrado no sistema");
			}
		}
		
		if(pessoa.isNova()) {
			pessoa.setDataCriacao(LocalDateTime.now());
		} else {
			Pessoa pessoaExistente = this.pessoas.findOne(pessoa.getId());
			pessoa.setDataCriacao(pessoaExistente.getDataCriacao());
		}
		
		this.publisher.publishEvent(new PessoaEvent(pessoa));
		
		return this.pessoas.saveAndFlush(pessoa);
		
	}

	@Transactional
	public void excluir(Pessoa pessoa) {
		try {
			String foto = pessoa.getFoto();
			this.pessoas.delete(pessoa);
			this.pessoas.flush();
			
			if(!StringUtils.isEmpty(foto)) {
				this.fotoStorage.excluirFoto(foto);
			}
		} catch(PersistenceException e) {
			throw new NegocioException("Erro tentando excluir pessoa");
		}
	}

}
