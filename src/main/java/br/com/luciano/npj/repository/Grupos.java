package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Grupo;
import br.com.luciano.npj.repository.helper.grupo.GruposQueries;

@Repository
public interface Grupos extends JpaRepository<Grupo, Integer>, GruposQueries {

}
