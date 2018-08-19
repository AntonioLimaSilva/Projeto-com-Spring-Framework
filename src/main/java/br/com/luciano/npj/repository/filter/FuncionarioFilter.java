package br.com.luciano.npj.repository.filter;

import br.com.luciano.npj.model.Pessoa;

public class FuncionarioFilter {

	private Pessoa pessoa;
	private String funcao;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
