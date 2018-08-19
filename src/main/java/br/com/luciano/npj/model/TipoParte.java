package br.com.luciano.npj.model;

public enum TipoParte {
	
	AUTOR("Autor(a)"),
	REPRESENTANTE("Representante"),
	REPRESENTADO("Representado");
	
	private String descricao;
	
	TipoParte(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
