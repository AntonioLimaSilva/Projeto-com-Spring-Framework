package br.com.luciano.npj.repository.helper.documento.processo;

import java.util.List;

import br.com.luciano.npj.model.DocumentoProcesso;
import br.com.luciano.npj.model.Processo;

public interface DocumentosProcessosQueries {
	
	List<DocumentoProcesso> buscarDocumentosPorProcesso(Processo processo);

}
