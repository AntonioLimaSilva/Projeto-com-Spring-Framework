package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Cidade cidade = new Cidade();
			cidade.setId(Integer.valueOf(id));
			return cidade;
		}
		return null;
	}

}
