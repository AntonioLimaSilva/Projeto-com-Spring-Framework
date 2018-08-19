package br.com.luciano.npj.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.model.Grupo;
import br.com.luciano.npj.repository.Grupos;
import br.com.luciano.npj.repository.Permissoes;
import br.com.luciano.npj.service.GrupoService;

@Controller
@RequestMapping("/grupos")
public class GruposController {
	
	@Autowired
	private Permissoes permissoes;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private Grupos grupos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
		
		mv.addObject("permissoes", this.permissoes.findAll());
		
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(grupo);
		}
		
		this.grupoService.salvar(grupo);
		
		attributes.addFlashAttribute("message", "Grupo salvo com sucesso");
		
		return new ModelAndView("redirect:/grupos/novo");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {			
		Optional<Grupo> grupoOptional = this.grupos.buscarPorId(id);
		
		if(grupoOptional.isPresent()) {
			Grupo grupo = grupoOptional.get();
			ModelAndView mv = novo(grupo);
			mv.addObject(grupo);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/grupos/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		this.grupoService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("grupo/PesquisaGrupos");
				
		mv.addObject("grupos", this.grupos.findAll());
		
		return mv;
	}
	
}
