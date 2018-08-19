package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Usuario;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pessoa.id", "", "Selecione um usuário na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "login", "", "Login é obrigatório");
		ValidationUtils.rejectIfEmpty(errors, "senha", "", "Senha é obrigatório");
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getGrupos().size() < 1) {
			errors.rejectValue("grupos", "", "Selecione pelo menos um grupo");
		}
	}

}
