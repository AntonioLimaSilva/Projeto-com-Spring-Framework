package br.com.luciano.npj.repository.helper.reu;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.repository.filter.ReuFilter;

public interface ReusQueries {
	
	Page<Reu> filtrar(ReuFilter filtro, Pageable pageable);
	
	Optional<Reu> buscarPorId(Integer id);

}
