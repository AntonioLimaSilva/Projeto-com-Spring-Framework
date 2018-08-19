package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Cabine;

@Repository
public interface Cabines extends JpaRepository<Cabine, Integer> {

}
