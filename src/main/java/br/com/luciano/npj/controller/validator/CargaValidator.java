package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Carga;

@Component
public class CargaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Carga.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "processo.id", "", "Selecione um processo na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "pessoa.id", "", "Selecione uma pessoa na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "dataSaida", "", "Data de saída é obrigatória");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "", "Descrição é obrigatória");
	}

}
