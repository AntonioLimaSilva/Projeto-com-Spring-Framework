package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Horario;

@Repository
public interface Horarios extends JpaRepository<Horario, Integer> {

}
