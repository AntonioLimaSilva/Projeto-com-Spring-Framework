package br.com.luciano.npj.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.luciano.npj.storage.DocumentoStorage;

public class DocumentoStorageLocal implements DocumentoStorage {
	
	private static final Logger LOGEER = LoggerFactory.getLogger(DocumentoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public DocumentoStorageLocal() {
		this(FileSystems.getDefault().getPath(System.getProperty("user.home"), "npjdocumentos"));
		//this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".npjdocumentos")); // Linux e MAC
	}

	public DocumentoStorageLocal(Path local) {
		this.local = local;
		criarPastas();
	}


	@Override
	public String salvarDocumentoTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if(files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + novoNome));
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("Erro salvando documento no local temporário");
			}
		}
		
		return novoNome;
	}

	@Override
	public void salvarDocumento(String nome) {
		try {
			Files.move(this.localTemporario.resolve(nome), this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo documento para local definitivo");
		}
	}

	@Override
	public byte[] recuperarDocumento(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro recuperando documento do local final");
		}
	}
	
	@Override
	public void excluirDocumento(String nome) {
		try {
			Files.deleteIfExists(local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro excluíndo documento do local final");
		}
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(LOGEER.isDebugEnabled()) {
				LOGEER.debug("Pastas criadas para salvar documentos!!");
				LOGEER.debug("Pasta default " + this.local.toAbsolutePath());
				LOGEER.debug("Pasta temporaria " + this.localTemporario.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para documento");
		}
		
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString().substring(0, 8) + "_" + nomeOriginal;
		novoNome = novoNome.replaceAll("\\s+", "_");
		
		if(LOGEER.isDebugEnabled()) {
			LOGEER.debug(String.format("Novo original: %s, novo nome: %s ", nomeOriginal, novoNome));
		}
		
		return novoNome;
	}

}
