package br.com.luciano.npj.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.luciano.npj.dto.AgendaDTO;
import br.com.luciano.npj.model.Agenda;
import br.com.luciano.npj.repository.Agendas;
import br.com.luciano.npj.service.AgendaService;

@Controller
@RequestMapping("/agendas")
public class AgendasController {
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private AgendaService agendaService;
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> salvar(@Valid Agenda agenda, BindingResult result) {		
		if(result.hasErrors()) {
			List<String> campos = result.getFieldErrors().stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
			return ResponseEntity.badRequest().body(campos);
		}
		
		this.agendaService.salvar(agenda);
		
		return ResponseEntity.ok("Ok");
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AgendaDTO> pesquisar() {
		return this.agendas.buscarTodas();
	}

}
