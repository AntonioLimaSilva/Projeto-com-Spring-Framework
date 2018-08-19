package br.com.luciano.npj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.luciano.npj.dto.ProcessoComAssistidoAlunoDTO;
import br.com.luciano.npj.model.Acao;
import br.com.luciano.npj.model.AcaoProcesso;
import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.model.AlunoProcesso;
import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.model.DocumentoProcesso;
import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.model.Processo;
import br.com.luciano.npj.model.TipoParte;
import br.com.luciano.npj.model.TipoParticipacao;
import br.com.luciano.npj.repository.Acoes;
import br.com.luciano.npj.repository.AcoesProcessos;
import br.com.luciano.npj.repository.Alunos;
import br.com.luciano.npj.repository.AlunosProcessos;
import br.com.luciano.npj.repository.Assistidos;
import br.com.luciano.npj.repository.AssistidosProcessos;
import br.com.luciano.npj.repository.Documentos;
import br.com.luciano.npj.repository.DocumentosProcessos;
import br.com.luciano.npj.repository.Funcionarios;
import br.com.luciano.npj.repository.FuncionariosProcessos;
import br.com.luciano.npj.repository.Processos;
import br.com.luciano.npj.service.ProcessoService;
import br.com.luciano.npj.service.RelatorioService;
import br.com.luciano.npj.service.exception.NegocioException;
import br.com.luciano.npj.session.TabelaItensAcaoSession;
import br.com.luciano.npj.session.TabelaItensAlunoSession;
import br.com.luciano.npj.session.TabelaItensAssistidoSession;
import br.com.luciano.npj.session.TabelaItensDocumentoSession;
import br.com.luciano.npj.session.TabelaItensFuncionarioSession;

@Controller
@RequestMapping("/processos")
public class ProcessosController {
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private Processos processos;
	
	@Autowired
	private Assistidos assistidos;
	
	@Autowired
	private AssistidosProcessos assistidosProcessos;
	
	@Autowired
	private Acoes acoes;
	
	@Autowired
	private AcoesProcessos acoesProcessoes;
	
	@Autowired
	private Alunos alunos;
	
	@Autowired
	private AlunosProcessos alunosProcessos;
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private FuncionariosProcessos funcionariosProcessos;
	
	@Autowired
	private Documentos documentos;
	
	@Autowired
	private DocumentosProcessos documentosProcessos;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private TabelaItensAssistidoSession tabelaItensAssistidoSession;
	
	@Autowired
	private TabelaItensFuncionarioSession tabelaItensFuncionarioSession;
	
	@Autowired
	private TabelaItensDocumentoSession tabelaItensDocumentoSession;
	
	@Autowired
	private TabelaItensAcaoSession tabelaItensAcaoSession;
	
	@Autowired
	private TabelaItensAlunoSession tabelaItensAlunoSession;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Processo processo) {
		ModelAndView mv = new ModelAndView("processo/CadastroProcesso");
		
		setUuid(processo);
		
		mv.addObject("tiposPartes", TipoParte.values());
		mv.addObject("tiposParticipacoes", TipoParticipacao.values());
		mv.addObject("acoes", this.acoes.findAll());	
		mv.addObject("itensAssistidosProcesso", tabelaItensAssistidoSession.getItens(processo.getUuid()));
		mv.addObject("itensFuncionariosProcesso", this.tabelaItensFuncionarioSession.getItens(processo.getUuid()));
		mv.addObject("itensDocumentosProcesso", this.tabelaItensDocumentoSession.getItens(processo.getUuid()));
		mv.addObject("itensAcoesProcesso", this.tabelaItensAcaoSession.getItens(processo.getUuid()));
		mv.addObject("itensAlunosProcesso", this.tabelaItensAlunoSession.getItens(processo.getUuid()));
			
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Processo processo, BindingResult result, Model model) {
		processo.adicionarItensAssistido(this.tabelaItensAssistidoSession.getItens(processo.getUuid()));
		processo.adicionarItensFuncionario(this.tabelaItensFuncionarioSession.getItens(processo.getUuid()));
		processo.adicionarItensDocumento(this.tabelaItensDocumentoSession.getItens(processo.getUuid()));
		processo.adicionarItensAcao(this.tabelaItensAcaoSession.getItens(processo.getUuid()));
		processo.adicionarItensAluno(this.tabelaItensAlunoSession.getItens(processo.getUuid()));
		
		try {
			this.processoService.salvar(processo);
		} catch(NegocioException e) {
			result.rejectValue("itensAssistido", e.getMessage(), e.getMessage());
			return novo(processo);
		} catch(RuntimeException e) {
			return new ModelAndView("redirect:/processos/novo");
		}
		
		model.addAttribute("message", "Processo criado com sucesso");
		
		ModelAndView mv = novo(processo);
		mv.addObject(processo);
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar() {	
		Processo processo = this.processos.buscarUltimoProcesso();
		
		if(processo != null) {
			setUuid(processo);
			
			adicionarAssistidos(processo);
			adicionarFuncionarios(processo);
			adicionarAlunos(processo);
			adicionarAcoes(processo);
			adicionarDocumentos(processo);
			
			ModelAndView mv = new ModelAndView("processo/PesquisaProcessos");
			mv.addObject("processo", processo);
			mv.addObject("itensAssistidosProcesso", tabelaItensAssistidoSession.getItens(processo.getUuid()));
			mv.addObject("itensFuncionariosProcesso", this.tabelaItensFuncionarioSession.getItens(processo.getUuid()));
			mv.addObject("itensDocumentosProcesso", this.tabelaItensDocumentoSession.getItens(processo.getUuid()));
			mv.addObject("itensAcoesProcesso", this.tabelaItensAcaoSession.getItens(processo.getUuid()));
			mv.addObject("itensAlunosProcesso", this.tabelaItensAlunoSession.getItens(processo.getUuid()));
			
			return mv;
		}
		
		return new ModelAndView("redirect:/processos/novo");
	}
	
	@RequestMapping(value = "filtrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProcessoComAssistidoAlunoDTO> filtrar(String nomeAssistidoOuAluno) {
		return this.processos.buscarProcessoComAssistidoAluno(nomeAssistidoOuAluno);
	}
	
	@GetMapping("/filtrar/{id}")
	public ModelAndView pesquisarPorId(@PathVariable Integer id) {
		Processo processo = this.processos.buscarProcessoPor(id);
		
		setUuid(processo);
		
		adicionarAssistidos(processo);
		adicionarFuncionarios(processo);
		adicionarAlunos(processo);
		adicionarAcoes(processo);
		adicionarDocumentos(processo);
		
		ModelAndView mv = new ModelAndView("processo/TimeLineComMembrosProcesso");
		mv.addObject("processo", processo);		
		mv.addObject("itensAssistidosProcesso", tabelaItensAssistidoSession.getItens(processo.getUuid()));
		mv.addObject("itensFuncionariosProcesso", this.tabelaItensFuncionarioSession.getItens(processo.getUuid()));
		mv.addObject("itensDocumentosProcesso", this.tabelaItensDocumentoSession.getItens(processo.getUuid()));
		mv.addObject("itensAcoesProcesso", this.tabelaItensAcaoSession.getItens(processo.getUuid()));
		mv.addObject("itensAlunosProcesso", this.tabelaItensAlunoSession.getItens(processo.getUuid()));
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		Processo processo = this.processos.buscarProcessoPor(id);
		
		if(processo != null) {
			setUuid(processo);
			
			adicionarAssistidos(processo);
			adicionarFuncionarios(processo);
			adicionarAlunos(processo);
			adicionarAcoes(processo);
			adicionarDocumentos(processo);
			
			ModelAndView mv = novo(processo);
			mv.addObject(processo);
			
			return mv;
		}
		
		return new ModelAndView("redirect:/processos/novo");
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Integer id) {
		this.processoService.excluir(id);
		
		return ResponseEntity.ok().build();
	}

	@PostMapping("/itemAssistido")
	public ModelAndView adicionarItemAssistido(Integer idAssistido, String uuid, TipoParte tipoParte) {
		Assistido assistido = assistidos.buscarAssistidoComPessoaPorId(idAssistido);
		this.tabelaItensAssistidoSession.adicionarItem(uuid, assistido, tipoParte);
		
		return mvTabelaItensAssistidoProcesso(uuid);
	}
	
	@DeleteMapping("/itemAssistido/{uuid}/{idAssistido}")
	public ModelAndView excluirItemAssistido(@PathVariable String uuid, @PathVariable Integer idAssistido) {
		Assistido assistido = assistidos.buscarAssistidoComPessoaPorId(idAssistido);
		this.tabelaItensAssistidoSession.excluirItem(uuid, assistido);
		
		return mvTabelaItensAssistidoProcesso(uuid);
	}
		
	@PostMapping("/itemFuncionario")
	public ModelAndView adicionarItemFuncionario(Integer idFuncionario, String uuid, TipoParticipacao tipoParticipacao) {
		Funcionario funcionario = this.funcionarios.buscarFuncionarioComPessoaPorId(idFuncionario);
		this.tabelaItensFuncionarioSession.adicionar(uuid, funcionario, tipoParticipacao);
		
		return mvTabelaItensFuncionarioProcesso(uuid);
	}

	@DeleteMapping("/itemFuncionario/{uuid}/{idFuncionario}")
	public ModelAndView excluirItemFuncionario(@PathVariable String uuid, @PathVariable Integer idFuncionario) {
		Funcionario funcionario = this.funcionarios.findOne(idFuncionario);
		this.tabelaItensFuncionarioSession.excluirItem(uuid, funcionario);
		
		return mvTabelaItensFuncionarioProcesso(uuid);
	}
	
	@PostMapping("/itemDocumento")
	public ModelAndView adicionarItemDocumento(Integer idDocumento, String uuid) {
		Documento documento = this.documentos.findOne(idDocumento);
		this.tabelaItensDocumentoSession.adicionarItem(uuid, documento);
		
		return mvTabelaItensDocumentoProcesso(uuid);
	}
	
	@DeleteMapping("/itemDocumento/{uuid}/{idDocumento}")
	public ModelAndView excluirItemDocumento(@PathVariable String uuid, @PathVariable Integer idDocumento) {
		Documento documento = this.documentos.findOne(idDocumento);
		this.tabelaItensDocumentoSession.excluirItemDocumento(uuid, documento);
		
		return mvTabelaItensDocumentoProcesso(uuid);
	}
	
	@PostMapping("/itemAcao")
	public ModelAndView adicionarItemAcao(Integer idAcao, String uuid) {
		Acao acao = this.acoes.findOne(idAcao);
		this.tabelaItensAcaoSession.adicionarItem(uuid, acao);
		
		return mvTabelaItensAcaoProcesso(uuid);
	}
	
	@DeleteMapping("/itemAcao/{uuid}/{idAcao}")
	public ModelAndView excluirItemAcao(@PathVariable String uuid, @PathVariable Integer idAcao) {
		Acao acao = this.acoes.findOne(idAcao);
		this.tabelaItensAcaoSession.excluirItemAcao(uuid, acao);
		
		return mvTabelaItensAcaoProcesso(uuid);
	}
	
	@PostMapping("/itemAluno")
	public ModelAndView adicionarItemAluno(Integer idAluno, String uuid) {
		Optional<Aluno> alunoOptional = this.alunos.buscarPorId(idAluno);
		
		if(alunoOptional.isPresent()) {		
			this.tabelaItensAlunoSession.adicionarItem(uuid, alunoOptional.get());
		}
		
		return mvTabelaItensAlunoProcesso(uuid);
	}
	
	@DeleteMapping("/itemAluno/{uuid}/{idAluno}")
	public ModelAndView excluirItemAluno(@PathVariable String uuid, @PathVariable Integer idAluno) {
		Aluno aluno = this.alunos.findOne(idAluno);
		this.tabelaItensAlunoSession.excluirItemAluno(uuid, aluno);
		
		return mvTabelaItensAlunoProcesso(uuid);
	}
	
	@GetMapping("/relatorio/{id}")
	public ResponseEntity<byte[]> gerarRelatorio(@PathVariable Integer id) throws Exception {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("id_processo", id);
		parametros.put("PATH_LOGO", new ClassPathResource("static/images/logo_relatorio.png").getPath());
		String caminhoArquivo = "/relatorios/processo/capa_processo_simples.jasper";
		
		byte[] relatorio = this.relatorioService.gerarRelatorio(id, parametros, caminhoArquivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}
	
	private ModelAndView mvTabelaItensAcaoProcesso(String uuid) {
		ModelAndView mv = new ModelAndView("processo/TabelaItensAcaoProcesso");
		mv.addObject("itensAcoesProcesso", this.tabelaItensAcaoSession.getItens(uuid));
		return mv;
	}

	private ModelAndView mvTabelaItensDocumentoProcesso(String uuid) {
		ModelAndView mv = new ModelAndView("processo/TabelaItensDocumentoProcesso");
		mv.addObject("itensDocumentosProcesso", this.tabelaItensDocumentoSession.getItens(uuid));
		return mv;
	}

	private ModelAndView mvTabelaItensAssistidoProcesso(String uuid) {
		ModelAndView mv = new ModelAndView("processo/TabelaItensAssistidoProcesso");
		mv.addObject("itensAssistidosProcesso", this.tabelaItensAssistidoSession.getItens(uuid));
		return mv;
	}
	
	private ModelAndView mvTabelaItensFuncionarioProcesso(String uuid) {
		ModelAndView mv = new ModelAndView("processo/TabelaItensFuncionarioProcesso");
		mv.addObject("itensFuncionariosProcesso", this.tabelaItensFuncionarioSession.getItens(uuid));
		return mv;
	}
	
	private ModelAndView mvTabelaItensAlunoProcesso(String uuid) {
		ModelAndView mv = new ModelAndView("processo/TabelaItensAlunoProcesso");
		mv.addObject("itensAlunosProcesso", this.tabelaItensAlunoSession.getItens(uuid));
		return mv;
	}
	
	private void setUuid(Processo processo) {
		if(StringUtils.isEmpty(processo.getUuid())) {
			processo.setUuid(UUID.randomUUID().toString());
		}
	}
	
	private void adicionarDocumentos(Processo processo) {
		List<DocumentoProcesso> itens = this.documentosProcessos.buscarDocumentosPorProcesso(processo);
		itens.forEach(item -> this.tabelaItensDocumentoSession.adicionarItem(processo.getUuid(), item.getDocumento()));
	}
	
	private void adicionarAcoes(Processo processo) {
		List<AcaoProcesso> itens = this.acoesProcessoes.buscarAcoesPorProcesso(processo);
		itens.forEach(item ->  this.tabelaItensAcaoSession.adicionarItem(processo.getUuid(), item.getAcao()));
	}
	
	private void adicionarAlunos(Processo processo) {
		List<AlunoProcesso> itens = this.alunosProcessos.buscarAlunosPorProcesso(processo);
		itens.forEach(item -> this.tabelaItensAlunoSession.adicionarItem(processo.getUuid(), item.getAluno()));
	}
	
	private void adicionarFuncionarios(Processo processo) {
		List<FuncionarioProcesso> itens =  this.funcionariosProcessos.buscarFuncionariosPorProcesso(processo);
		itens.forEach(item -> {
			this.tabelaItensFuncionarioSession.adicionar(processo.getUuid(), item.getFuncionario(), item.getTipoParticipacao());
		});
	}
	
	private void adicionarAssistidos(Processo processo) {
		List<AssistidoProcesso> itens = this.assistidosProcessos.buscarAssistidosPorProcesso(processo);
		itens.forEach(item -> {
			this.tabelaItensAssistidoSession.adicionarItem(processo.getUuid(), item.getAssistido(), item.getTipoParte());		
		});
	}
}