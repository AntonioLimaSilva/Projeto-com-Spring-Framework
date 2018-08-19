package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.repository.helper.assistido.processo.AssistidosProcessosQueries;

@Repository
public interface AssistidosProcessos extends JpaRepository<AssistidoProcesso, Integer>, AssistidosProcessosQueries {

}
