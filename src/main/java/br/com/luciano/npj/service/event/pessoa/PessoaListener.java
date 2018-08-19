package br.com.luciano.npj.service.event.pessoa;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.service.AssistidoService;
import br.com.luciano.npj.service.ReuService;
import br.com.luciano.npj.storage.FotoStorage;

@Component
public class PessoaListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired
	private AssistidoService assistidoService;
	
	@Autowired
	private ReuService reuService;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")
	public void salvarFoto(PessoaEvent evento) {
		this.fotoStorage.salvarFoto(evento.getPessoa().getFoto());
	}

	@AliasFor(value = "salvarTransformarAssistido")
	public void salvarAssistido(PessoaEvent pessoaEvent) {
		Assistido assistido = new Assistido();
		assistido.setDataCriacao(LocalDateTime.now());
		assistido.setPessoa(pessoaEvent.getPessoa());
		
		this.assistidoService.salvar(assistido);
	}
	
	@AliasFor(value = "salvarTransformarReu")
	public void salvarReu(PessoaEvent pessoaEvent) {
		Reu reu = new Reu();
		reu.setDataCriacao(LocalDateTime.now());
		reu.setPessoa(pessoaEvent.getPessoa());
		
		this.reuService.salvar(reu);
	}
}
