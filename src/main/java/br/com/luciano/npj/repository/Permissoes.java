package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Permissao;
import br.com.luciano.npj.repository.helper.permissao.PermissoesQueries;

@Repository
public interface Permissoes extends JpaRepository<Permissao, Integer>, PermissoesQueries {

}
