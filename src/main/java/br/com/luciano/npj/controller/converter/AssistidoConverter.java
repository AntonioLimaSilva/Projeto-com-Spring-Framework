package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Assistido;

public class AssistidoConverter implements Converter<String, Assistido> {

	@Override
	public Assistido convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Assistido assistido = new Assistido();
			assistido.setId(Integer.valueOf(id));
			return assistido;
		}
		return null;
	}

}
