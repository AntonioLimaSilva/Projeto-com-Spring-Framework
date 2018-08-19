package br.com.luciano.npj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.luciano.npj.dto.FotoDTO;
import br.com.luciano.npj.storage.FotoStorage;
import br.com.luciano.npj.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
			
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
		thread.start();
		
		return resultado;
	}

	// .* é para o spring entender a extensão da foto
	// método chamado no cadastro de pessoa
	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		return this.fotoStorage.recuperarFotoTemporaria(nome);
	}
	
	// método chamado na pesquisa de pessoas
	@GetMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome) {
		return this.fotoStorage.recuperarFoto(nome);
	}
}
