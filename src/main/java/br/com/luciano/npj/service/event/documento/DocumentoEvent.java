package br.com.luciano.npj.service.event.documento;

import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Documento;

public class DocumentoEvent {
	
	private Documento documento;
	
	public DocumentoEvent(Documento documento) {
		this.documento = documento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public boolean temDocumento() {
		return !StringUtils.isEmpty(this.documento.getNome()) && documento.isNovo();
	}
}
