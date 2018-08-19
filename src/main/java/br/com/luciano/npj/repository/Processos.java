package br.com.luciano.npj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Processo;
import br.com.luciano.npj.repository.helper.processo.ProcessosQueries;

@Repository
public interface Processos extends JpaRepository<Processo, Integer>, ProcessosQueries {

	Processo findTopByOrderByIdDesc();

}
