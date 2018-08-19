package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Cabine;

public class CabineConverter implements Converter<String, Cabine> {

	@Override
	public Cabine convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Cabine cabine = new Cabine();
			cabine.setId(Integer.valueOf(id));
			return cabine;
		}
		return null;
	}

}
