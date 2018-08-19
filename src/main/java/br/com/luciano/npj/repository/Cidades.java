package br.com.luciano.npj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Integer> {
	
	public List<Cidade> findByEstadoId(Integer idEstado);

}
