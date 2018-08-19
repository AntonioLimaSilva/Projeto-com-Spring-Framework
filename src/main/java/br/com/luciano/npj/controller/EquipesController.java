package br.com.luciano.npj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.model.Equipe;
import br.com.luciano.npj.service.EquipeService;

@Controller
@RequestMapping("/equipes")
public class EquipesController {
	
	@Autowired
	private EquipeService equipeService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Equipe equipe) {
		ModelAndView mv = new ModelAndView("equipe/CadastroEquipe");
		
		return mv;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Equipe equipe, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return nova(equipe);
		}
		
		this.equipeService.salvar(equipe);
		
		attributes.addFlashAttribute("message", "Equipe salva com sucesso!");
		
		return new ModelAndView("redirect:/equipes/nova");
	}

}
