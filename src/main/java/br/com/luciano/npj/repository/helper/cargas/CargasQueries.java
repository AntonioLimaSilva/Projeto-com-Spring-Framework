package br.com.luciano.npj.repository.helper.cargas;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.CargaDTO;
import br.com.luciano.npj.model.Carga;
import br.com.luciano.npj.repository.filter.CargaFilter;

public interface CargasQueries {
	
	Page<CargaDTO> filtrar(Pageable pageable, CargaFilter filtro);
	
	Optional<Carga> buscarPorId(Integer id);

}
