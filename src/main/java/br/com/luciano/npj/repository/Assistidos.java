package br.com.luciano.npj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.repository.helper.assistido.AssistidosQueries;

@Repository
public interface Assistidos extends JpaRepository<Assistido, Integer>, AssistidosQueries {

	public List<Assistido> findByPessoaNomeStartingWithIgnoreCase(String nome);

}
