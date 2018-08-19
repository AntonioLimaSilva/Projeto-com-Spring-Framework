package br.com.luciano.npj.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.controller.page.PageWrapper;
import br.com.luciano.npj.controller.validator.ExpedienteValidator;
import br.com.luciano.npj.model.Expediente;
import br.com.luciano.npj.repository.Disciplinas;
import br.com.luciano.npj.repository.Expedientes;
import br.com.luciano.npj.repository.Turmas;
import br.com.luciano.npj.repository.filter.ExpedienteFilter;
import br.com.luciano.npj.service.ExpedienteService;

@Controller
@RequestMapping("/expedientes")
public class ExpedientesController {
	
	@Autowired
	private Expedientes expedientes;
	
	@Autowired
	private ExpedienteService expedienteService;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Turmas turmas;
	
	@Autowired
	private ExpedienteValidator validator;
	
	@InitBinder("expediente")
	public void inicializarValidator(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Expediente expediente) {
		ModelAndView mv = new ModelAndView("expediente/CadastroExpediente");	
		mv.addObject("disciplinas", this.disciplinas.findAll());
		mv.addObject("turmas", this.turmas.buscarTurmas());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Expediente Expediente, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(Expediente);
		}
		
		this.expedienteService.salvar(Expediente);
		attributes.addFlashAttribute("message", "Expediente salva com sucesso");
		
		return new ModelAndView("redirect:/expedientes/novo");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Expediente> expedienteOptional = this.expedientes.buscarPor(id);
		
		if(expedienteOptional.isPresent()) {
			Expediente expediente = expedienteOptional.get();
			ModelAndView mv = novo(expediente);
			mv.addObject(expediente);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/expedientes/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.expedienteService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar(ExpedienteFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("expediente/PesquisaExpedientes");
		PageWrapper<Expediente> pagina = new PageWrapper<>(this.expedientes.filtrar(pageable, filtro), httpServletRequest);
		mv.addObject("pagina", pagina);
		
		return mv;
	}

}
