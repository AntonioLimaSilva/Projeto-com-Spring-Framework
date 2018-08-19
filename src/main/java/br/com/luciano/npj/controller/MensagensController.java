package br.com.luciano.npj.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.luciano.npj.controller.validator.MensagemValidator;
import br.com.luciano.npj.dto.MensagemConteudoDTO;
import br.com.luciano.npj.dto.MensagemDTO;
import br.com.luciano.npj.model.Mensagem;
import br.com.luciano.npj.repository.Mensagens;
import br.com.luciano.npj.security.UsuarioSistema;
import br.com.luciano.npj.service.MensagemService;

@Controller
@RequestMapping("/mensagens")
public class MensagensController {
	
	@Autowired
	private MensagemService mensagemService;
	
	@Autowired
	private Mensagens mensagens;
	
	@Autowired
	private MensagemValidator mensagemValidator;
	
	@InitBinder("mensagem")
	public void inicializarValidator(WebDataBinder binder) {
		binder.setValidator(mensagemValidator);
	}

	@RequestMapping("/nova")
	public ModelAndView nova(Mensagem mensagem) {
		ModelAndView mv = new ModelAndView("mensagem/CadastroMensagem");
		
		return mv;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Mensagem mensagem, BindingResult result, 
			RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuario) {
		if(result.hasErrors()) {
			return nova(mensagem);
		}
		
		mensagem.setUsuarioAtivo(usuario.getUsuario());
		this.mensagemService.salvar(mensagem);
		
		attributes.addFlashAttribute("message", "Mensagem salva com sucesso");
		return new ModelAndView("redirect:/mensagens/nova");
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MensagemDTO> pesquisar(@AuthenticationPrincipal UsuarioSistema usuario) {
		return mensagens.buscarParaUsuario(usuario != null ? usuario.getUsuario().getId() : -1);
	}
	
	@RequestMapping(value = "/filtrar/{idMensagem}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MensagemConteudoDTO pesquisar(@PathVariable("idMensagem") Integer id) {
		return mensagens.pesquisar(id).isPresent() ? mensagens.pesquisar(id).get() : null;
	}
	
	@RequestMapping(value = "/total", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long totalMensagensValidas(@AuthenticationPrincipal UsuarioSistema usuario) {
		return this.mensagens.totalMensagensValidas(usuario != null ? usuario.getUsuario().getId() : -1);
	}
	
}
