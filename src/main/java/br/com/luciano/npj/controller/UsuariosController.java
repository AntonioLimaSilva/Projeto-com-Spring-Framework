package br.com.luciano.npj.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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
import br.com.luciano.npj.controller.validator.UsuarioValidator;
import br.com.luciano.npj.dto.UsuarioDTO;
import br.com.luciano.npj.model.Grupo;
import br.com.luciano.npj.model.Usuario;
import br.com.luciano.npj.repository.Grupos;
import br.com.luciano.npj.repository.Usuarios;
import br.com.luciano.npj.repository.filter.UsuarioFilter;
import br.com.luciano.npj.service.UsuarioService;
import br.com.luciano.npj.service.exception.LoginUsuarioJaExistenteException;
import br.com.luciano.npj.service.exception.NegocioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@InitBinder("usuario")
	public void iniciarValidator(WebDataBinder binder) {
		binder.setValidator(this.usuarioValidator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", this.grupos.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		validarUsuario(usuario, result);
		
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			this.usuarioService.salvar(usuario);
		} catch(NegocioException e) {
			result.rejectValue("confirmacaoSenha", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(LoginUsuarioJaExistenteException e) {
			result.rejectValue("login", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter filtro, @PageableDefault(size = 20) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuarios");
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(filtro, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}	
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Optional<Usuario> usuarioOptional = this.usuarios.buscarPorId(id);
		
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			
			ModelAndView mv = novo(usuario);
			mv.addObject(usuario);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {		
		this.usuarioService.excluir(id);
		
		return ResponseEntity.ok().build();
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UsuarioDTO> pesquisarPor(String nome) {
		return this.usuarios.buscarUsuariosAtivosPorNome(nome);
	}
	
	private void validarUsuario(Usuario usuario, BindingResult result) {
		if(usuario.isExistente() && usuario.getGrupos().isEmpty()) {
			Optional<Usuario> usuarioOptional = this.usuarios.buscarPorId(usuario.getId());
			for(Grupo grupo: usuarioOptional.get().getGrupos()) {
				usuario.getGrupos().add(grupo);
			}
		}
		
		this.usuarioValidator.validate(usuario, result);
	}

}
