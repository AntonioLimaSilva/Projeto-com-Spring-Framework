package br.com.luciano.npj.repository.helper.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.PessoaDTO;
import br.com.luciano.npj.dto.PessoaProcessoDTO;
import br.com.luciano.npj.model.Pessoa;
import br.com.luciano.npj.repository.filter.PessoaFilter;
import br.com.luciano.npj.security.UsuarioSistema;

public interface PessoasQueries {
	
	Page<Pessoa> filtrar(PessoaFilter filtro, Pageable pageable, UsuarioSistema usuario);
	
	List<PessoaDTO> buscarTodosQueNaoEstaoEmUsuario(String nome);
	
	List<PessoaDTO> buscarTodosQueNaoEstaoEmAssistido(String nome);
	
	List<PessoaDTO> buscarTodosQueNaoEstaoEmFuncionario(String nome);
	
	List<PessoaDTO> buscarTodosQueNaoEstaoEmReu(String nome);
	
	List<PessoaDTO> buscarTodosQueNaoEstaoEmAluno(String nome);
	
	List<PessoaDTO> buscarPessoasPorNome(String nome);
	
	List<PessoaProcessoDTO> buscarPessoasComProcesso(String nome);
	
	Optional<Pessoa> buscarPorId(Integer id);

}
