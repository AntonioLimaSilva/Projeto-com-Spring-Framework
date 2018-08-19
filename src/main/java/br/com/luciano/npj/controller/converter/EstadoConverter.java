package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Estado;

public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Estado estado = new Estado();
			estado.setId(Integer.valueOf(id));
			return estado;
		}
		return null;
	}

}
