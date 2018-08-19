package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Agenda;
import br.com.luciano.npj.repository.helper.agenda.AgendasQueries;

@Repository
public interface Agendas extends JpaRepository<Agenda, Integer>, AgendasQueries {

}
