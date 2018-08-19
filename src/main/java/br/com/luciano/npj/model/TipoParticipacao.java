package br.com.luciano.npj.model;

public enum TipoParticipacao {
	
	PROFESSOR("Professor(a)"),
	ATENDENTE("Atendente"),
	COORDENADOR("Coordenador(a)"),
	DEFENSOR("Defensor(a)"),
	ASSISTENTE_SOCIAL("Assistente Social"),
	MEDIADOR("Mediador(a)"),
	SUPERVISOR("Supervisor(a)");
	
	private String descricao;

	TipoParticipacao(String descriacao) {
		this.descricao = descriacao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
