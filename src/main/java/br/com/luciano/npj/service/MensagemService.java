package br.com.luciano.npj.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Mensagem;
import br.com.luciano.npj.repository.Mensagens;

@Service
public class MensagemService {
	
	@Autowired
	private Mensagens mensagens;
	
	@Transactional
	public void salvar(Mensagem mensagem) {
		mensagem.setDataInicio(LocalDate.now());
		mensagem.setDataFim(LocalDate.now().plusDays(5));
		
		this.mensagens.save(mensagem);
	}

}
