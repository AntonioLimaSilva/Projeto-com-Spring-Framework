package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Mensagem;

@Component
public class MensagemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Mensagem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "usuarioPassivo.id", "", "Selecione uma pessoa na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "titulo", "", "Título é obrigatória");
		ValidationUtils.rejectIfEmpty(errors, "conteudo", "", "Conteúdo é obrigatória");
	}

}
