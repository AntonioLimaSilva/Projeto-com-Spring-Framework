package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.repository.helper.funcionario.processo.FuncionariosProcessosQueries;

@Repository
public interface FuncionariosProcessos extends JpaRepository<FuncionarioProcesso, Integer>, FuncionariosProcessosQueries {

}
