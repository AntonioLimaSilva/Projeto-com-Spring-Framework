package br.com.luciano.npj.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String salvarFoto(MultipartFile[] files);
	
	public void salvarFoto(String nome);

	public byte[] recuperarFotoTemporaria(String nome);
	
	public byte[] recuperarFoto(String nome);

	public void excluirFoto(String foto);

}
