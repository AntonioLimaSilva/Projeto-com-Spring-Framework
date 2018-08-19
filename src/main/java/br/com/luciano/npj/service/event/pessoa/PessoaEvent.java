package br.com.luciano.npj.service.event.pessoa;

import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Pessoa;

public class PessoaEvent {
	
	private Pessoa pessoa;
	
	public PessoaEvent(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.pessoa.getFoto());
	}
	
	public boolean isNovaFoto() {
		return this.pessoa.isNovaFoto();
	}

}