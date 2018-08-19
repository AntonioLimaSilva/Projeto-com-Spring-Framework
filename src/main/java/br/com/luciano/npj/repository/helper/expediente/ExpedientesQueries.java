package br.com.luciano.npj.repository.helper.expediente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.model.Expediente;
import br.com.luciano.npj.repository.filter.ExpedienteFilter;

public interface ExpedientesQueries {
	
	Page<Expediente> filtrar(Pageable pageable, ExpedienteFilter filtro);
	
	Optional<Expediente> buscarPor(Integer id);
	
	List<Expediente> buscarExpedientes();

}
