package br.com.luciano.npj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.luciano.npj.controller.page.PageWrapper;
import br.com.luciano.npj.model.Disciplina;
import br.com.luciano.npj.repository.Disciplinas;
import br.com.luciano.npj.repository.filter.DisciplinaFilter;
import br.com.luciano.npj.service.DisciplinaService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinasController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@RequestMapping("/nova")
	public ModelAndView novo(Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroDisciplina");		
		return mv;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Disciplina disciplina, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(disciplina);
		}
		
		this.disciplinaService.salvar(disciplina);
		attributes.addFlashAttribute("message", "Disciplina salva com sucesso");
		
		return new ModelAndView("redirect:/disciplinas/nova");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Disciplina disciplina = this.disciplinas.findOne(id);
		
		if(disciplina != null) {
			ModelAndView mv = novo(disciplina);
			mv.addObject(disciplina);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/disciplinas/nova");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		this.disciplinaService.exluir(id);
				
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar(DisciplinaFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("disciplina/PesquisaDisciplinas");
		
		PageWrapper<Disciplina> pagina = new PageWrapper<>(this.disciplinas.filtrar(pageable, filtro), httpServletRequest);
		mv.addObject("pagina", pagina);
		
		return mv;
	}

}
