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

import br.com.luciano.npj.model.Turma;
import br.com.luciano.npj.repository.Horarios;
import br.com.luciano.npj.repository.Turmas;
import br.com.luciano.npj.service.TurmaService;

@Controller
@RequestMapping("/turmas")
public class TurmasController {
	
	@Autowired
	private Horarios horarios;
	
	@Autowired
	private Turmas turmas;
	
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping("/nova")
	public ModelAndView novo(Turma turma) {
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		
		mv.addObject("horarios", this.horarios.findAll());
		
		return mv;
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Turma turma, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(turma);
		}
		
		this.turmaService.salvar(turma);
		attributes.addFlashAttribute("message", "Turma salva com sucesso");
		
		return new ModelAndView("redirect:/turmas/nova");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {			
		Optional<Turma> turmaOptional = this.turmas.buscarPorId(id);
		
		if(turmaOptional.isPresent()) {
			Turma turma = turmaOptional.get();
			ModelAndView mv = novo(turma);
			mv.addObject(turma);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/turmas/nova");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.turmaService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("turma/PesquisaTurmas");
				
		mv.addObject("turmas", this.turmas.findAll());
		
		return mv;
	}

}
