package br.com.luciano.npj.dto;

public class FotoDTO {

	private String nomeFoto;
	private String contentType;

	public FotoDTO(String nomeFoto, String contentType) {
		this.nomeFoto = nomeFoto;
		this.contentType = contentType;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public String getContentType() {
		return contentType;
	}

}
