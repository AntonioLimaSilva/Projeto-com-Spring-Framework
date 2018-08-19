package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Grupo grupo = new Grupo();
			grupo.setId(Integer.valueOf(id));
			return grupo;
		}
		return null;
	}

}
