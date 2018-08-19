package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.AlunoProcesso;
import br.com.luciano.npj.repository.helper.aluno.processo.AlunosProcessosQueries;

@Repository
public interface AlunosProcessos extends JpaRepository<AlunoProcesso, Integer>, AlunosProcessosQueries {

}
