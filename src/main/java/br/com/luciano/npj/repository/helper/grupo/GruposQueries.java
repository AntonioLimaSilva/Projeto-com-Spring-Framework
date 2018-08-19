package br.com.luciano.npj.repository.helper.grupo;

import java.util.Optional;

import br.com.luciano.npj.model.Grupo;

public interface GruposQueries {
	
	Optional<Grupo> buscarPorId(Integer id);

}
