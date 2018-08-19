package br.com.luciano.npj.service.event.documento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.luciano.npj.storage.DocumentoStorage;

@Component
public class DocumentoListener {
	
	@Autowired
	private DocumentoStorage documentoStorage;
	
	@EventListener(condition = "#evento.temDocumento()")
	public void documentoSalvo(DocumentoEvent evento) {
		this.documentoStorage.salvarDocumento(evento.getDocumento().getNome());
	}

}
