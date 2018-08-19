package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Expediente;
import br.com.luciano.npj.repository.helper.expediente.ExpedientesQueries;

@Repository
public interface Expedientes extends JpaRepository<Expediente, Integer>, ExpedientesQueries {	

}
