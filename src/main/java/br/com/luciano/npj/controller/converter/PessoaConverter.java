package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Pessoa;

public class PessoaConverter implements Converter<String, Pessoa> {

	@Override
	public Pessoa convert(String id) {		
		if(!StringUtils.isEmpty(id)) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(Integer.valueOf(id));
			return pessoa;
		}
		
		return null;
	}

}
