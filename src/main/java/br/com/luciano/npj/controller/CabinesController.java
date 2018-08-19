package br.com.luciano.npj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.model.Cabine;
import br.com.luciano.npj.service.CabineService;

@Controller
@RequestMapping("/cabines")
public class CabinesController {
	
	@Autowired
	private CabineService cabineService;
	
	@RequestMapping("/nova")
	public ModelAndView novo(Cabine cabine) {
		ModelAndView mv = new ModelAndView("cabine/CadastroCabine");		
		return mv;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cabine cabine, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cabine);
		}
		
		this.cabineService.salvar(cabine);
		attributes.addFlashAttribute("message", "Cabine salva com sucesso");
		
		return new ModelAndView("redirect:/cabines/nova");
	}

}
