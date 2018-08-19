package br.com.luciano.npj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.luciano.npj.model.Permissao;
import br.com.luciano.npj.repository.Permissoes;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {
	
	@Autowired
	private Permissoes permissoes; 
	
	@RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Permissao> pesquisarPorIdEstado(@PathVariable(name = "id") Integer idGrupo) {
		return this.permissoes.buscarPermissoesPorGrupo(idGrupo);
	}

}
