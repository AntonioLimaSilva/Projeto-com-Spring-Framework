package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Equipe;

public class EquipeConverter implements Converter<String, Equipe> {

	@Override
	public Equipe convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Equipe equipe = new Equipe();
			equipe.setId(Integer.valueOf(id));
			return equipe;
		}
		return null;
	}

}
