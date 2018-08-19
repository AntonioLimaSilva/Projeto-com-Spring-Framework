package br.com.luciano.npj.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import br.com.luciano.npj.controller.validator.CargaValidator;
import br.com.luciano.npj.dto.CargaDTO;
import br.com.luciano.npj.model.Carga;
import br.com.luciano.npj.repository.Cargas;
import br.com.luciano.npj.repository.filter.CargaFilter;
import br.com.luciano.npj.security.UsuarioSistema;
import br.com.luciano.npj.service.CargaService;

@Controller
@RequestMapping("/cargas")
public class CargasController {
	
	@Autowired
	private Cargas cargas;
	
	@Autowired
	private CargaService cargaService;
	
	@Autowired
	private CargaValidator cargaValidator;
	
	@InitBinder("carga")
	public void iniciarValidator(WebDataBinder binder) {
		binder.setValidator(this.cargaValidator);
	}
	
	@RequestMapping("/nova")
	public ModelAndView nova(Carga carga) {
		ModelAndView mv = new ModelAndView("carga/CadastroCarga");
		
		return mv;
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Carga carga, BindingResult result, RedirectAttributes attributes,
			@AuthenticationPrincipal UsuarioSistema usuario) {
		if(result.hasErrors()) {
			return nova(carga);
		}
		
		carga.setUsuario(usuario.getUsuario());
		this.cargaService.salvar(carga);
		attributes.addFlashAttribute("message", "Carga salva com sucessso");
		return new ModelAndView("redirect:/cargas/nova");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Carga> cargaOptional = this.cargas.buscarPorId(id);
		
		if(cargaOptional.isPresent()) {
			Carga carga = cargaOptional.get();
			ModelAndView mv = nova(carga);
			mv.addObject(carga);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/cargas/nova");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		this.cargaService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar(CargaFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("carga/PesquisaCargas");
		
		PageWrapper<CargaDTO> pagina = new PageWrapper<>(this.cargas.filtrar(pageable, filtro), httpServletRequest);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
}
