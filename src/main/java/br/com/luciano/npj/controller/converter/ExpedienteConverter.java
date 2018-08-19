package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Expediente;

public class ExpedienteConverter implements Converter<String, Expediente> {

	@Override
	public Expediente convert(String id) {		
		if(!StringUtils.isEmpty(id)) {
			Expediente expediente = new Expediente();
			expediente.setId(Integer.valueOf(id));
			return expediente;
		}
		
		return null;
	}

}
