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
import br.com.luciano.npj.controller.validator.ReuValidator;
import br.com.luciano.npj.model.Reu;
import br.com.luciano.npj.repository.Reus;
import br.com.luciano.npj.repository.filter.ReuFilter;
import br.com.luciano.npj.service.ReuService;

@Controller
@RequestMapping("/reus")
public class ReusController {
	
	@Autowired
	private ReuService reuService;
	
	@Autowired
	private Reus reus;
	
	@Autowired
	private ReuValidator validator;
	
	@InitBinder("reu")
	public void iniciarValidator(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}

	@RequestMapping("/novo")
	public ModelAndView novo(Reu reu) {
		ModelAndView mv = new ModelAndView("reu/CadastroReu");
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Reu reu, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(reu);
		}
		
		this.reuService.salvar(reu);
		attributes.addFlashAttribute("message", "Réu salvo com sucesso");
		return new ModelAndView("redirect:/reus/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ReuFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {		
		ModelAndView mv = new ModelAndView("reu/PesquisaReus");
		
		PageWrapper<Reu> paginaWrapper = new PageWrapper<>(this.reus.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Reu> reuOptional = this.reus.buscarPorId(id);
		
		if(reuOptional.isPresent()) {
			Reu reu = reuOptional.get();
			
			ModelAndView mv = novo(reu);
			mv.addObject(reu);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/reus/novo");
 	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.reuService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
}
