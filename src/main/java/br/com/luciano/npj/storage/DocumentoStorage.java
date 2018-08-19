package br.com.luciano.npj.storage;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentoStorage {
	
	public String salvarDocumentoTemporariamente(MultipartFile[] files);
	
	public void salvarDocumento(String nome);
	
	public byte[] recuperarDocumento(String nome);

	public void excluirDocumento(String nome);

}
