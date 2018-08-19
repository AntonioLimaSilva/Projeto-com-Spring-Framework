package br.com.luciano.npj.repository.helper.aluno.processo;

import java.util.List;

import br.com.luciano.npj.model.AlunoProcesso;
import br.com.luciano.npj.model.Processo;

public interface AlunosProcessosQueries {
	
	List<AlunoProcesso> buscarAlunosPorProcesso(Processo processo);

}
