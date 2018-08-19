package br.com.luciano.npj.repository.helper.assistido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.AssistidoDTO;
import br.com.luciano.npj.dto.AssistidoMesDTO;
import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.repository.filter.AssistidoFilter;

public interface AssistidosQueries {
	
	Page<Assistido> filtrar(AssistidoFilter filtro, Pageable pageable);
	
	List<AssistidoDTO> buscarPessoaAssitidoPorNome(String nome);
	
	Assistido buscarAssistidoComPessoaPorId(Integer idAssistido);
	
	List<AssistidoMesDTO> buscarTotalMes();
	
	Optional<Assistido> buscarPorId(Integer id);

}
