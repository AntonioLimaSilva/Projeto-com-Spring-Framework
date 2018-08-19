package br.com.luciano.npj.dto;

public class AlunoDTO {

	private Integer id;
	private String nome;
	private String pseudonimo;
	private String cpf;

	public AlunoDTO(Integer id, String nome, String pseudonimo, String cpf) {
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
