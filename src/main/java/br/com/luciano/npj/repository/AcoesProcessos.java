package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.AcaoProcesso;
import br.com.luciano.npj.repository.helper.acao.processo.AcoesProcessosQueries;

@Repository
public interface AcoesProcessos extends JpaRepository<AcaoProcesso, Integer>, AcoesProcessosQueries {

}
