package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.repository.helper.reu.ReusQueries;

@Repository
public interface Reus extends JpaRepository<Reu, Integer>, ReusQueries {

}
