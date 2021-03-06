package br.com.luciano.npj.dto;

public class FuncionarioDTO {
	
	private Integer id;
	private String nome;
	private String pseudonimo;
	private String cpf;
	
	public FuncionarioDTO(Integer id, String nome, String pseudonimo, String cpf) {
		this.id = id;
		this.nome = nome;
		this.pseudonimo = pseudonimo;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPseudonimo() {
		return pseudonimo;
	}

	public String getCpf() {
		return cpf;
	}

}
