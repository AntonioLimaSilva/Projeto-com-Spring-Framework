package br.com.luciano.npj.dto;

public class ProcessoComAssistidoAlunoDTO {

	private Integer id;
	private String assistido;
	private String aluno;

	public ProcessoComAssistidoAlunoDTO(Integer id, String assistido, String aluno) {
		this.id = id;
		this.assistido = assistido;
		this.aluno = aluno;
	}

	public Integer getId() {
		return id;
	}

	public String getAssistido() {
		return assistido;
	}

	public String getAluno() {
		return aluno;
	}

}
