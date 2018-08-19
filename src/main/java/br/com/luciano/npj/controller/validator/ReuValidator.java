package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Reu;

@Component
public class ReuValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Reu.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pessoa.id", "", "Selecione um Reu na pesquisa rápida");
	}

}
