package br.com.luciano.npj.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Permissao;

public class PermissaoConverter implements Converter<String, Permissao> {

	@Override
	public Permissao convert(String id) {
		
		if(!StringUtils.isEmpty(id)) {
			Permissao permissao = new Permissao();
			permissao.setId(Integer.valueOf(id));
			return permissao;
		}
		return null;
	}

}
