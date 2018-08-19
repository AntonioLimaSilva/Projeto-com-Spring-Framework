package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.repository.helper.aluno.AlunosQueries;

@Repository
public interface Alunos extends JpaRepository<Aluno, Integer>, AlunosQueries {

}
