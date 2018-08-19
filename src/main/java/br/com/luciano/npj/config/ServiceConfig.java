package br.com.luciano.npj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.luciano.npj.service.PessoaService;
import br.com.luciano.npj.storage.DocumentoStorage;
import br.com.luciano.npj.storage.FotoStorage;
import br.com.luciano.npj.storage.local.DocumentoStorageLocal;
import br.com.luciano.npj.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = PessoaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
	@Bean
	public DocumentoStorage documentoStorage() {
		return new DocumentoStorageLocal();
	}

}
