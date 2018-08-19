package br.com.luciano.npj.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.controller.page.PageWrapper;
import br.com.luciano.npj.dto.DocumentoDTO;
import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.repository.Documentos;
import br.com.luciano.npj.repository.filter.DocumentoFilter;
import br.com.luciano.npj.service.DocumentoService;
import br.com.luciano.npj.service.exception.NegocioException;
import br.com.luciano.npj.storage.DocumentoStorage;
import br.com.luciano.npj.storage.DocumentoStorageRunnable;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {
	
	@Autowired
	private DocumentoStorage documentoStorage;
	
	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private Documentos documentos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Documento documento) {
		ModelAndView mv = new ModelAndView("documento/CadastroDocumento");
		
		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Documento documento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(documento);
		}
		
		try {
			this.documentoService.salvar(documento);
		} catch(NegocioException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(documento);
		}
		attributes.addFlashAttribute("message", "Documento salvo com sucesso");
		return new ModelAndView("redirect:/documentos/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		Documento documento = this.documentos.findOne(id);	
		this.documentoService.excluir(documento);
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(DocumentoFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("documento/PesquisaDocumentos");
		
		PageWrapper<Documento> paginaWrapper = new PageWrapper<>(this.documentos.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Documento documento = this.documentos.findOne(id);
		
		ModelAndView mv = novo(documento);
		mv.addObject(documento);
		
		return mv;
	}
	
	@PostMapping("/upload")
	public @ResponseBody DeferredResult<DocumentoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<DocumentoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new DocumentoStorageRunnable(files, resultado, documentoStorage));
		thread.start();
		
		return resultado;
	}
	
	@GetMapping("/local/{nome:.*}")
	public @ResponseBody byte[] recuperarDocumento(@PathVariable String nome) {
		return this.documentoStorage.recuperarDocumento(nome);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Documento> pesquisarPorNome(String nome) {
		validarTamanhoNome(nome);
		return this.documentos.findByNomeContainingIgnoreCase(nome);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody ResponseEntity<Void> ilegalArgumentoException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() <= 3) {
			throw new IllegalArgumentException();
		}
	}

}
