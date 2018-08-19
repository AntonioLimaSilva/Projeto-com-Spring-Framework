package br.com.luciano.npj.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.controller.page.PageWrapper;
import br.com.luciano.npj.controller.validator.AlunoValidator;
import br.com.luciano.npj.dto.AlunoDTO;
import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.repository.Alunos;
import br.com.luciano.npj.repository.Cabines;
import br.com.luciano.npj.repository.Equipes;
import br.com.luciano.npj.repository.Expedientes;
import br.com.luciano.npj.repository.filter.AlunoFilter;
import br.com.luciano.npj.service.AlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private Alunos alunos;
	
	@Autowired
	private Cabines cabines;
	
	@Autowired
	private Equipes equipes;
	
	@Autowired
	private Expedientes expedientes;
	
	@Autowired
	private AlunoValidator alunoValidator;
	
	@InitBinder("aluno")
	public void inicializarValidator(WebDataBinder binder) {
		binder.setValidator(alunoValidator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Aluno aluno) {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		
		mv.addObject("cabines", this.cabines.findAll());
		mv.addObject("equipes", this.equipes.findAll());
		mv.addObject("expedientes", this.expedientes.buscarExpedientes());
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(aluno);
		}
		
		this.alunoService.salvar(aluno);
		
		attributes.addFlashAttribute("message", "Aluno salvo com sucesso");
		
		return new ModelAndView("redirect:/alunos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AlunoFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("aluno/PesquisaAlunos");
		
		PageWrapper<Aluno> pagina = new PageWrapper<>(this.alunos.filtrar(pageable, filtro), httpServletRequest);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Aluno> alunoOptional = this.alunos.buscarPorId(id);
		
		if(alunoOptional.isPresent()) {
			Aluno aluno = alunoOptional.get();
			ModelAndView mv = novo(aluno);
			mv.addObject(aluno);
			return mv;
		}
		
		return new ModelAndView("redirect:/alunos/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.alunoService.excluir(id);		
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlunoDTO> buscarPorNome(String nome) {
		validarTamanhoNome(nome);
		return this.alunos.buscarPessoaAlunoPorNome(nome);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody ResponseEntity<Void> tratarIlegalArgumentoException() {
		return ResponseEntity.badRequest().build();
	}
	
	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() <= 3) {
			throw new IllegalArgumentException();
		}
	}

}