package br.com.luciano.npj.repository.filter;

import br.com.luciano.npj.model.Pessoa;

public class UsuarioFilter {

	private Pessoa pessoa;
	private String login;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
