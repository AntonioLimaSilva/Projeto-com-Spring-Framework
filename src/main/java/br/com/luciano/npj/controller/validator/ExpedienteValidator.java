package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Expediente;

@Component
public class ExpedienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Expediente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "funcionario.id", "", "Selecione um funcionário na pesquisa rápida");	
		ValidationUtils.rejectIfEmpty(errors, "periodoLetivo", "", "Periodo letivo é obrigatório");
		ValidationUtils.rejectIfEmpty(errors, "disciplina.id", "", "Disciplina é obrigatório");
		
		Expediente expediente = (Expediente) target;
		
		if(expediente.getTurmas().size() < 1) {
			errors.rejectValue("turmas", "", "Marque uma turma para esse expediente");
		}
	}

}
