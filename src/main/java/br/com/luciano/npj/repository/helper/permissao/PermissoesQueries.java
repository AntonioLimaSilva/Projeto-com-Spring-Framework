package br.com.luciano.npj.repository.helper.permissao;

import java.util.List;

import br.com.luciano.npj.model.Permissao;

public interface PermissoesQueries {
	
	List<Permissao> buscarPermissoesPorGrupo(Integer idGrupo);

}
