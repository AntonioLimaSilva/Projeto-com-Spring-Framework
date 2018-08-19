package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Turma;

public class TurmaConverter implements Converter<String, Turma> {

	@Override
	public Turma convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Turma turma = new Turma();
			turma.setId(Integer.valueOf(id));
			return turma;
		}
		return null;
	}

}
