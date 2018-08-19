package br.com.luciano.npj.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.luciano.npj.model.Pessoa;

@Component
public class PessoaValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		return Pessoa.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "", "Nome deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pseudonimo", "", "Pseudônimo deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foneCelular", "", "Celular deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "", "CPF deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estadoCivil", "", "Estado civil deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro", "", "Logradouro deve ser informado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cep", "", "CEP deve ser informado");
		ValidationUtils.rejectIfEmpty(errors, "endereco.estado.id", "", "Estado deve ser informado");
		ValidationUtils.rejectIfEmpty(errors, "endereco.cidade.id", "", "Cidade deve ser informada");
	}

}
