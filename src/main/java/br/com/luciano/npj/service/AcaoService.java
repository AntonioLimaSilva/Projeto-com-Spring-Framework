package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Acao;
import br.com.luciano.npj.repository.Acoes;

@Service
public class AcaoService {
	
	@Autowired
	private Acoes acoes;
	
	@Transactional
	public void salvar(Acao acao) {
		if(acao.isNova()) {
			acao.setDataCriacao(LocalDateTime.now());
		} else {
			Acao acaoExistente = this.acoes.findOne(acao.getId());
			acao.setDataCriacao(acaoExistente.getDataCriacao());
		}
		
		this.acoes.save(acao);
	}

}
