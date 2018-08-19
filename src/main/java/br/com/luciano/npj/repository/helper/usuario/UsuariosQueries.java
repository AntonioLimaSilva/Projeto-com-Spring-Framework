package br.com.luciano.npj.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.luciano.npj.dto.UsuarioDTO;
import br.com.luciano.npj.model.Usuario;
import br.com.luciano.npj.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	Optional<Usuario> buscarPorLoginAtivo(String login);
	
	List<UsuarioDTO> buscarUsuariosAtivosPorNome(String nome);
	
	List<String> buscarPermissoes(Usuario usuario);
	
	Optional<Usuario> buscarPorId(Integer id);

}
