package br.com.luciano.npj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Pessoa;
import br.com.luciano.npj.repository.helper.pessoa.PessoasQueries;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, Integer>, PessoasQueries {

	@Deprecated
	List<Pessoa> findByNomeStartingWithIgnoreCase(String nome);
	
	public Pessoa findById(Integer id);

	Pessoa findByCpf(String cpf);

}
