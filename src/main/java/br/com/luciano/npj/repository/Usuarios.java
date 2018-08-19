package br.com.luciano.npj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.npj.model.Usuario;
import br.com.luciano.npj.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer>, UsuariosQueries {

	Optional<Usuario> findByLoginIgnoreCase(String login);

}
