package br.com.luciano.npj.repository.helper.documento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.repository.filter.DocumentoFilter;

public interface DocumentosQueries {
	
	public Page<Documento> filtrar(DocumentoFilter filtro, Pageable pageable);

}
