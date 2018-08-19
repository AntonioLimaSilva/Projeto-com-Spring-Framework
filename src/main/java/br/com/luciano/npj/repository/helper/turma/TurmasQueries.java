package br.com.luciano.npj.repository.helper.turma;

import java.util.List;
import java.util.Optional;

import br.com.luciano.npj.model.Turma;

public interface TurmasQueries {
	
	List<Turma> buscarTurmas();
	
	Optional<Turma> buscarPorId(Integer id);

}
