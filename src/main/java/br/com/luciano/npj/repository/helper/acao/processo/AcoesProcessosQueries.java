package br.com.luciano.npj.repository.helper.acao.processo;

import java.util.List;

import br.com.luciano.npj.model.AcaoProcesso;
import br.com.luciano.npj.model.Processo;

public interface AcoesProcessosQueries {
	
	List<AcaoProcesso> buscarAcoesPorProcesso(Processo processo);

}
