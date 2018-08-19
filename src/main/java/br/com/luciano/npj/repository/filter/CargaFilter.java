package br.com.luciano.npj.repository.filter;

public class CargaFilter {
	
	private String nomePessoa;

	public CargaFilter(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

}
