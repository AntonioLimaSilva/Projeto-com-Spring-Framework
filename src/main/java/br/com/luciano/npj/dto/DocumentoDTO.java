package br.com.luciano.npj.dto;

public class DocumentoDTO {

	private String nomeDocumento;
	private String contentType;
	private long tamanho;

	public DocumentoDTO(String nomeDocumento, String contentType, long tamanho) {
		this.nomeDocumento = nomeDocumento;
		this.contentType = contentType;
		this.tamanho = tamanho;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public String getContentType() {
		return contentType;
	}

	public long getTamanho() {
		return tamanho;
	}

}
