package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Disciplina;

public class DisciplinaConverter implements Converter<String, Disciplina> {

	@Override
	public Disciplina convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Disciplina disciplina = new Disciplina();
			disciplina.setId(Integer.valueOf(id));
			return disciplina;
		}
		return null;
	}

}
