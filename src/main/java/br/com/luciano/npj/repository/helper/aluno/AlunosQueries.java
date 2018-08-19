package br.com.luciano.npj.repository.helper.aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.AlunoDTO;
import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.repository.filter.AlunoFilter;

public interface AlunosQueries {
	
	public List<AlunoDTO> buscarPessoaAlunoPorNome(String nome);
	
	public Optional<Aluno> buscarPorId(Integer id);
	
	Optional<Aluno> buscarAlunoPorId(Integer idUsuario);
	
	Page<Aluno> filtrar(Pageable pageable, AlunoFilter filtro);

}
