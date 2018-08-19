package br.com.luciano.npj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
import br.com.luciano.npj.controller.validator.AssistidoValidator;
import br.com.luciano.npj.dto.AssistidoDTO;
import br.com.luciano.npj.dto.AssistidoMesDTO;
import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.repository.Assistidos;
import br.com.luciano.npj.repository.filter.AssistidoFilter;
import br.com.luciano.npj.service.AssistidoService;
import br.com.luciano.npj.service.RelatorioService;

@Controller
@RequestMapping("/assistidos")
public class AssistidosController {
	
	@Autowired
	private AssistidoService assistidoService;
	
	@Autowired
	private Assistidos assistidos;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private AssistidoValidator validator;
	
	@InitBinder("assistido")
	public void inicializarValidator(WebDataBinder binder) {
		binder.setValidator(this.validator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Assistido assistido) {
		ModelAndView mv = new ModelAndView("assistido/CadastroAssistido");		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Assistido assistido, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(assistido);
		}
		
		this.assistidoService.salvar(assistido);
		attributes.addFlashAttribute("message", "Assistido salvo com sucesso");
		return new ModelAndView("redirect:/assistidos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AssistidoFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("assistido/PesquisaAssistidos");
		
		PageWrapper<Assistido> paginaWrapper = new PageWrapper<>(this.assistidos.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Assistido> assistidoOptional = this.assistidos.buscarPorId(id);
		
		if(assistidoOptional.isPresent()) {
			Assistido assistido = assistidoOptional.get();
			ModelAndView mv = novo(assistido);
			mv.addObject(assistido);
			return mv;
		}
		
		return new ModelAndView("redirect:/assistido/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {	
		this.assistidoService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AssistidoDTO> buscarAssistidosPorNome(String nome) {
		validarTamanhoNome(nome);
		return this.assistidos.buscarPessoaAssitidoPorNome(nome);
	}
	
	@RequestMapping(value = "/quantidadeAssistidoNoMes")
	public @ResponseBody List<AssistidoMesDTO> buscarQuantidadeAssistidoPorMes() {
		return assistidos.buscarTotalMes();
	}
	
	@GetMapping("/relatorio/{id}")
	public ResponseEntity<byte[]> gerarRelatorio(@PathVariable Integer id) throws Exception {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("id_assistido", id);
		parametros.put("PATH_LOGO", new ClassPathResource("static/images/logo_relatorio.png").getPath());
		String caminhoArquivo = "/relatorios/assistido/capa_assistido.jasper";
		
		byte[] relatorio = this.relatorioService.gerarRelatorio(id, parametros, caminhoArquivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}
	
	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}

}
