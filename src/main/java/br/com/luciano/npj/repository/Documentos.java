package br.com.luciano.npj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.repository.helper.documento.DocumentosQueries;

@Repository
public interface Documentos extends JpaRepository<Documento, Integer>, DocumentosQueries {

	List<Documento> findByNomeContainingIgnoreCase(String nome);

}
