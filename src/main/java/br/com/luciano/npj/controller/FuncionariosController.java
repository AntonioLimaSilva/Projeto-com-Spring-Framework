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
import br.com.luciano.npj.controller.validator.FuncionarioValidator;
import br.com.luciano.npj.dto.FuncionarioDTO;
import br.com.luciano.npj.dto.FuncionarioMesDTO;
import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.repository.Funcionarios;
import br.com.luciano.npj.repository.filter.FuncionarioFilter;
import br.com.luciano.npj.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	public FuncionarioValidator validator;
	
	@InitBinder("funcionario")
	public void iniciarValidator(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}

	@RequestMapping("/novo")
	public ModelAndView novo(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("funcionario/CadastroFuncionario");
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(funcionario);
		}
		
		this.funcionarioService.salvar(funcionario);
		attributes.addFlashAttribute("message", "Funcionário salvo com sucesso");
		return new ModelAndView("redirect:/funcionarios/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(FuncionarioFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("funcionario/PesquisaFuncionarios");
		
		PageWrapper<Funcionario> paginaWrapper = new PageWrapper<>(this.funcionarios.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Funcionario> funcionarioOptional = this.funcionarios.buscarPorId(id);
		
		if(funcionarioOptional.isPresent()) {
			Funcionario funcionario = funcionarioOptional.get();
			
			ModelAndView mv = novo(funcionario);
			mv.addObject(funcionario);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/funcionarios/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.funcionarioService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/expediente", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FuncionarioDTO> buscarParaExpediente(String nome) {
		validarTamanhoNome(nome);
		
		return this.funcionarios.buscarFuncionarioQueNaoEstaoExpediente(nome);
	}
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FuncionarioDTO> buscarFuncionarioPorNome(String nome) {
		validarTamanhoNome(nome);
		
		return this.funcionarios.buscarPessoaFuncionarioPorNome(nome);
	}
	
	@RequestMapping(value = "/totalProcessosNoMes")
	public @ResponseBody List<FuncionarioMesDTO> buscarQuantidadeAssistidoPorMes() {
		return funcionarios.buscarTotalMes();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIlegalArgumentoException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 4) {
			throw new IllegalArgumentException("Digite pelo menos três letras na pesquisa");
		}
	}
}
