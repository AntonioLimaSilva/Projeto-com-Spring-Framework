package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Funcionario;

public class FuncionarioConverter implements Converter<String, Funcionario> {

	@Override
	public Funcionario convert(String id) {		
		if(!StringUtils.isEmpty(id)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(Integer.valueOf(id));
			return funcionario;
		}
		
		return null;
	}

}
