package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Turma;
import br.com.luciano.npj.repository.helper.turma.TurmasQueries;

@Repository
public interface Turmas extends JpaRepository<Turma, Integer>, TurmasQueries {

}
