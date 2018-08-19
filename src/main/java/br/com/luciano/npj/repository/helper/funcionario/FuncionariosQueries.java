package br.com.luciano.npj.repository.helper.funcionario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.FuncionarioDTO;
import br.com.luciano.npj.dto.FuncionarioMesDTO;
import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.repository.filter.FuncionarioFilter;

public interface FuncionariosQueries {
	
	Page<Funcionario> filtrar(FuncionarioFilter filtro, Pageable pageable);
	
	List<FuncionarioDTO> buscarFuncionarioQueNaoEstaoExpediente(String nome);
	
	List<FuncionarioDTO> buscarPessoaFuncionarioPorNome(String nome);
	
	Funcionario buscarFuncionarioComPessoaPorId(Integer idFuncionario);
	
	List<FuncionarioMesDTO> buscarTotalMes();
	
	Optional<Funcionario> buscarPorId(Integer id);
	
	

}
