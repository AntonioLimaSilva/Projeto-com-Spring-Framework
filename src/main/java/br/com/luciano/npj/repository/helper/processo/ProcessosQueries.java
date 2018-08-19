package br.com.luciano.npj.repository.helper.processo;

import java.util.List;

import br.com.luciano.npj.dto.ProcessoComAssistidoAlunoDTO;
import br.com.luciano.npj.model.Processo;

public interface ProcessosQueries {
	
	Processo buscarProcessoPor(Integer id);
	
	List<ProcessoComAssistidoAlunoDTO> buscarProcessoComAssistidoAluno(String nomeAssistidoOuAluno);
	
	Processo buscarUltimoProcesso();

}
