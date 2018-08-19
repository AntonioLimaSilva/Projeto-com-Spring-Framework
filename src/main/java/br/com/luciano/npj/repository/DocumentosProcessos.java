package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.DocumentoProcesso;
import br.com.luciano.npj.repository.helper.documento.processo.DocumentosProcessosQueries;

@Repository
public interface DocumentosProcessos extends JpaRepository<DocumentoProcesso, Integer>, DocumentosProcessosQueries {

}
