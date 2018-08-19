package br.com.luciano.npj.repository.helper.assistido.processo;

import java.util.List;

import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.model.Processo;

public interface AssistidosProcessosQueries {
	
	List<AssistidoProcesso> buscarAssistidosPorProcesso(Processo processo);

}
