package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.repository.helper.funcionario.FuncionariosQueries;

@Repository
public interface Funcionarios extends JpaRepository<Funcionario, Integer>, FuncionariosQueries {

}