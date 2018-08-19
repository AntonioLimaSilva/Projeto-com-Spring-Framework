package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Disciplina;
import br.com.luciano.npj.repository.helper.disciplina.DisciplinasQueries;

@Repository
public interface Disciplinas extends JpaRepository<Disciplina, Integer>, DisciplinasQueries {

}
