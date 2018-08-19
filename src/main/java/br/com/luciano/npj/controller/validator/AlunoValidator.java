package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Aluno;

@Component
public class AlunoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Aluno.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "pessoa.id", "", "Selecione um aluno na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "matricula", "", "Matrícula é obrigatória");
		ValidationUtils.rejectIfEmpty(errors, "cabine.id", "", "Cabine é obrigatória");
		ValidationUtils.rejectIfEmpty(errors, "equipe.id", "", "Equipe é obrigatória");
		ValidationUtils.rejectIfEmpty(errors, "expediente.id", "", "Expediente é obrigatório");
	}

}
