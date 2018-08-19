package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Carga;
import br.com.luciano.npj.repository.helper.cargas.CargasQueries;

@Repository
public interface Cargas extends JpaRepository<Carga, Integer>, CargasQueries {

}
