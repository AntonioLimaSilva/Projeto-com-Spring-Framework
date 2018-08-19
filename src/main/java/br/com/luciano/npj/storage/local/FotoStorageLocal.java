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

import br.com.luciano.npj.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {
	
	private static final Logger LOGEER = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(FileSystems.getDefault().getPath(System.getProperty("user.home"), "npjfotos"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}

	@Override
	public String salvarFoto(MultipartFile[] files) {
		String novoNome = null;
		if(files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toString() + FileSystems.getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return novoNome;
	}
	
	@Override
	public void salvarFoto(String nome) {
		try {
			Files.move(this.localTemporario.resolve(nome), this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final", e);
		}
		
		try {
			Thumbnails.of(this.local.resolve(nome).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando thumbnail", e);
		}	
	}	
	
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária", e);
		}
	}
	
	@Override
	public byte[] recuperarFoto(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto", e);
		}
	}
	
	@Override
	public void excluirFoto(String foto) {
		try {
			Files.deleteIfExists(local.resolve(foto));
		} catch (IOException e) {
			LOGEER.debug("Erro excluíndo foto do local final " + e);
			throw new RuntimeException("Erro excluindo foto do local final");
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(LOGEER.isDebugEnabled()) {
				LOGEER.debug("Pastas criadas para salvar fotos!!");
				LOGEER.debug("Pasta default " + this.local.toAbsolutePath());
				LOGEER.debug("Pasta temporaria " + this.localTemporario.toAbsolutePath());
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if(LOGEER.isDebugEnabled()) {
			LOGEER.debug(String.format("Novo original: %s, novo nome: %s ", nomeOriginal, novoNome));
		}
		
		return novoNome;
	}


}
