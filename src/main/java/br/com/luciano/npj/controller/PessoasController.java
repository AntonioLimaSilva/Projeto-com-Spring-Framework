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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import br.com.luciano.npj.controller.validator.PessoaValidator;
import br.com.luciano.npj.dto.PessoaDTO;
import br.com.luciano.npj.dto.PessoaProcessoDTO;
import br.com.luciano.npj.model.EstadoCivil;
import br.com.luciano.npj.model.Pessoa;
import br.com.luciano.npj.model.Sexo;
import br.com.luciano.npj.repository.Estados;
import br.com.luciano.npj.repository.Pessoas;
import br.com.luciano.npj.repository.filter.PessoaFilter;
import br.com.luciano.npj.security.UsuarioSistema;
import br.com.luciano.npj.service.PessoaService;
import br.com.luciano.npj.service.event.pessoa.PessoaEvent;
import br.com.luciano.npj.service.event.pessoa.PessoaListener;
import br.com.luciano.npj.service.exception.CpfJaCadastradoException;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaValidator pessoaValidator;
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private PessoaListener listener;
	
	@InitBinder("pessoa")
	public void iniciarValidator(WebDataBinder binder) {
		binder.setValidator(this.pessoaValidator);
	}
	
	@RequestMapping("/nova")
	public ModelAndView nova(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoa/CadastroPessoa");
		
		mv.addObject("sexos", Sexo.values());
		mv.addObject("estadosCivis", EstadoCivil.values());
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
	@RequestMapping(value = {"/nova", "{\\d+}"}, method = RequestMethod.POST, params = "salvar")
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {			
			return nova(pessoa);
		}
		
		try {
			this.pessoaService.salvar(pessoa);
		} catch(CpfJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return nova(pessoa);
		}
		
		attributes.addFlashAttribute("message", "Pessoa salva com sucesso");
		
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@RequestMapping(value = {"/nova", "{\\d+}"}, method = RequestMethod.POST, params = "salvarTransformarAssistido")
	public ModelAndView salvarTransformarAssistido(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {			
			return nova(pessoa);
		}
		
		try {
			Pessoa pessoaRetorno = this.pessoaService.salvar(pessoa);
			
			this.listener.salvarAssistido(new PessoaEvent(pessoaRetorno));
		} catch(CpfJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return nova(pessoa);
		}
		
		attributes.addFlashAttribute("message", "Pessoa salva e tranformada em assistido com sucesso");
		
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@RequestMapping(value = {"/nova", "{\\d+}"}, method = RequestMethod.POST, params = "salvarTransformarReu")
	public ModelAndView salvarTransformarReu(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {			
			return nova(pessoa);
		}
		
		try {
			Pessoa pessoaRetorno = this.pessoaService.salvar(pessoa);
			
			this.listener.salvarReu(new PessoaEvent(pessoaRetorno));
		} catch(CpfJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return nova(pessoa);
		}
		
		attributes.addFlashAttribute("message", "Pessoa salva e tranformada em réu com sucesso");
		
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PessoaFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuario) {
		ModelAndView mv = new ModelAndView("pessoa/PesquisaPessoas");
		
		PageWrapper<Pessoa> paginaWrapper = new PageWrapper<>(pessoas.filtrar(filtro, pageable, usuario), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Pessoa> pessoaOptional = this.pessoas.buscarPorId(id);
		
		if(pessoaOptional.isPresent()) {
			Pessoa pessoa = pessoaOptional.get();
			pessoa.getEndereco().setEstado(pessoa.getEndereco().getCidade().getEstado());
			
			ModelAndView mv = nova(pessoa);
			mv.addObject(pessoa);
			return mv;
		}
		
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		Pessoa pessoa = this.pessoas.findOne(id);		
		this.pessoaService.excluir(pessoa);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/pesquisar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisarPessoasPorNome(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarPessoasPorNome(nome);
	}
	
	/**
	 * Método esta sendo consumido via json pelo dialog da pesquisa de usuários
	 * @param nome
	 * @return Uma lista de pessoas que não estão em usuário ainda
	 */
	@RequestMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisarUsuario(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarTodosQueNaoEstaoEmUsuario(nome);
	}
	
	/**
	 * Método esta sendo consumido via json pelo dialog da pesquisa de asistidos
	 * @param nome
	 * @return Uma lista de pessoas que não estão em assistido ainda
	 */
	@RequestMapping(value = "/assistido", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisarAssistido(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarTodosQueNaoEstaoEmAssistido(nome);
	}
	
	/**
	 * Método esta sendo consumido via json pelo dialog da pesquisa de funcionarios
	 * @param nome
	 * @return Uma lista de pessoas que não estão em usuário ainda
	 */
	@RequestMapping(value = "/funcionario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisarFuncionario(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarTodosQueNaoEstaoEmFuncionario(nome);
	}

	@RequestMapping(value = "/reu", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisaReu(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarTodosQueNaoEstaoEmReu(nome);
	}
	
	@RequestMapping(value = "/aluno", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaDTO> pesquisaPorAluno(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarTodosQueNaoEstaoEmAluno(nome);
	}
	
	@RequestMapping(value = "/filtrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PessoaProcessoDTO> pesquisarPessoas(String nome) {
		validarTamanhoNome(nome);
		return this.pessoas.buscarPessoasComProcesso(nome);
	}
		
	//Esse exception handler é executado somente nesse controller
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}

	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}		
	}
}
