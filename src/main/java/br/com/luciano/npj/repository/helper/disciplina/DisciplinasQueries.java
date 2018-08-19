package br.com.luciano.npj.repository.helper.disciplina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.model.Disciplina;
import br.com.luciano.npj.repository.filter.DisciplinaFilter;

public interface DisciplinasQueries {
	
	Page<Disciplina> filtrar(Pageable pageable, DisciplinaFilter filtro);

}
