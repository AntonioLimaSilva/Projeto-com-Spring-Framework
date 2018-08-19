package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Mensagem;
import br.com.luciano.npj.repository.helper.mensagem.MensagensQueries;

@Repository
public interface Mensagens extends JpaRepository<Mensagem, Integer>, MensagensQueries {

}
