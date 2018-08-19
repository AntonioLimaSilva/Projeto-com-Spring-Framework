package br.com.luciano.npj.dto;

public class MensagemConteudoDTO {

	private String nomePessoaPassiva;
	private String nomePessoaAtiva;
	private String conteudo;

	public MensagemConteudoDTO(String nomePessoaPassiva, String nomePessoaAtiva, String conteudo) {
		this.nomePessoaPassiva = nomePessoaPassiva;
		this.nomePessoaAtiva = nomePessoaAtiva;
		this.conteudo = conteudo;
	}

	public String getNomePessoaPassiva() {
		return nomePessoaPassiva;
	}
	
	public String getNomePessoaAtiva() {
		return nomePessoaAtiva;
	}

	public String getConteudo() {
		return conteudo;
	}

}
