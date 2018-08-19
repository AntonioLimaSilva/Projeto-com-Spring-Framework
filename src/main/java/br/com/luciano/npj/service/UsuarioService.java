package br.com.luciano.npj.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Usuario;
import br.com.luciano.npj.repository.Usuarios;
import br.com.luciano.npj.service.exception.LoginUsuarioJaExistenteException;
import br.com.luciano.npj.service.exception.NegocioException;

@Service
public class UsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = this.usuarios.findByLoginIgnoreCase(usuario.getLogin());
		
		if(usuarioOptional.isPresent() && !usuarioOptional.get().equals(usuario)) {
			throw new LoginUsuarioJaExistenteException("Login do usuário já cadastrado");
		}
		
		if(usuario != null && !usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
			throw new NegocioException("Confirmação de senha não confere");
		}
		
		if(usuario.isExistente()) {
			Usuario usuarioExistente = this.usuarios.findOne(usuario.getId());
			usuario.setDataCriacao(usuarioExistente.getDataCriacao());
		}
		
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		this.usuarios.save(usuario);
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			this.usuarios.delete(id);
			this.usuarios.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo usuário");
		}
	}

}
