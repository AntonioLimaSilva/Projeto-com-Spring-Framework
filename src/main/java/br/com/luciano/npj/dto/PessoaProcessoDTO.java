package br.com.luciano.npj.dto;

public class PessoaProcessoDTO {

	private String nome;
	private String cpf;
	private Integer idProcesso;
	private String localizacao;

	public PessoaProcessoDTO(String nome, String cpf, Integer idProcesso, String localizacao) {
		this.nome = nome;
		this.cpf = cpf;
		this.idProcesso = idProcesso;
		this.localizacao = localizacao;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public Integer getIdProcesso() {
		return idProcesso;
	}

	public String getLocalizacao() {
		return localizacao;
	}

}
