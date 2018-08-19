package br.com.luciano.npj.repository.helper.funcionario.processo;

import java.util.List;

import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.model.Processo;

public interface FuncionariosProcessosQueries {
	
	List<FuncionarioProcesso> buscarFuncionariosPorProcesso(Processo processo);

}
