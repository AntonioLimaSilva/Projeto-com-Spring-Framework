package br.com.luciano.npj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.model.Acao;
import br.com.luciano.npj.service.AcaoService;

@Controller
@RequestMapping("/acoes")
public class AcoesController {
	
	@Autowired
	private AcaoService acaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Acao acao) {
		ModelAndView mv = new ModelAndView("acao/CadastroAcao");
		
		return mv;
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Acao acao, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return nova(acao);
		}
		
		this.acaoService.salvar(acao);
		attributes.addFlashAttribute("message", "Açao cadastrada com sucesso!");
		
		return new ModelAndView("redirect:/acoes/nova");
	}
}
