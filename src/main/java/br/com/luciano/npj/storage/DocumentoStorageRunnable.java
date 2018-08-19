package br.com.luciano.npj.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.luciano.npj.dto.DocumentoDTO;

public class DocumentoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<DocumentoDTO> resultado;
	private DocumentoStorage documentoStorage;

	public DocumentoStorageRunnable(MultipartFile[] files, DeferredResult<DocumentoDTO> resultado,
			DocumentoStorage documentoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.documentoStorage = documentoStorage;
		
	}

	@Override
	public void run() {
		String nomeDocumento = this.documentoStorage.salvarDocumentoTemporariamente(files);
		String contentType = this.files[0].getContentType();
		long tamanho = this.files[0].getSize();
		this.resultado.setResult(new DocumentoDTO(nomeDocumento, contentType, tamanho));
	}

}
