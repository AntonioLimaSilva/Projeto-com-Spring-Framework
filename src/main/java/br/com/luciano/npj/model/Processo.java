package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "NPJ_REAL_processo")
@DynamicUpdate
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String doFato;
	private String doDireito;
	private String doPedido;
	private LocalDateTime dataCriacao;
	private String documentosQueFaltam;
	private String localizacao;
	private Boolean estaComAluno;
	private Boolean extinto;
	private String uuid;
	private List<AssistidoProcesso> itensAssistido = new ArrayList<>();
	private List<FuncionarioProcesso> itensFuncionario = new ArrayList<>();
	private List<DocumentoProcesso> itensDocumento = new ArrayList<>();
	private List<AcaoProcesso> itensAcao = new ArrayList<>();
	private List<AlunoProcesso> itensAluno = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "do_fato")
	public String getDoFato() {
		return doFato;
	}

	public void setDoFato(String doFato) {
		this.doFato = doFato;
	}

	@Column(name = "do_direito")
	public String getDoDireito() {
		return doDireito;
	}

	public void setDoDireito(String doDireito) {
		this.doDireito = doDireito;
	}

	@Column(name = "do_pedido")
	public String getDoPedido() {
		return doPedido;
	}

	public void setDoPedido(String doPedido) {
		this.doPedido = doPedido;
	}

	@Column(name = "data_criacao", nullable = false)
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "documentos_que_faltam")
	public String getDocumentosQueFaltam() {
		return documentosQueFaltam;
	}

	public void setDocumentosQueFaltam(String documentosQueFaltam) {
		this.documentosQueFaltam = documentosQueFaltam;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Column(name = "esta_com_aluno")
	public Boolean getEstaComAluno() {
		return estaComAluno;
	}

	public void setEstaComAluno(Boolean estaComAluno) {
		this.estaComAluno = estaComAluno;
	}

	public Boolean getExtinto() {
		return extinto;
	}

	public void setExtinto(Boolean extinto) {
		this.extinto = extinto;
	}
	
	@Transient
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<AssistidoProcesso> getItensAssistido() {
		return itensAssistido;
	}

	public void setItensAssistido(List<AssistidoProcesso> itensAssistido) {
		this.itensAssistido = itensAssistido;
	}
	
	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<FuncionarioProcesso> getItensFuncionario() {
		return itensFuncionario;
	}
	
	public void setItensFuncionario(List<FuncionarioProcesso> itensFuncionario) {
		this.itensFuncionario = itensFuncionario;
	}

	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<DocumentoProcesso> getItensDocumento() {
		return itensDocumento;
	}

	public void setItensDocumento(List<DocumentoProcesso> itensDocumento) {
		this.itensDocumento = itensDocumento;
	}

	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<AcaoProcesso> getItensAcao() {
		return itensAcao;
	}

	public void setItensAcao(List<AcaoProcesso> itensAcao) {
		this.itensAcao = itensAcao;
	}

	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<AlunoProcesso> getItensAluno() {
		return itensAluno;
	}

	public void setItensAluno(List<AlunoProcesso> itensAluno) {
		this.itensAluno = itensAluno;
	}

	public void adicionarItensAssistido(List<AssistidoProcesso> itensAssistido) {
		this.itensAssistido = itensAssistido;
		this.itensAssistido.forEach(itemAssistido -> itemAssistido.setProcesso(this));	
	}
	
	public void adicionarItensFuncionario(List<FuncionarioProcesso> itensFuncionario) {
		this.itensFuncionario = itensFuncionario;
		this.itensFuncionario.forEach(itemFuncionario -> itemFuncionario.setProcesso(this));
	}
	
	public void adicionarItensDocumento(List<DocumentoProcesso> itens) {
		this.itensDocumento = itens;
		this.itensDocumento.forEach(item -> item.setProcesso(this));
	}
	
	public void adicionarItensAcao(List<AcaoProcesso> itens) {
		this.itensAcao = itens;
		this.itensAcao.forEach(item -> item.setProcesso(this));
	}
	
	public void adicionarItensAluno(List<AlunoProcesso> itens) {
		this.itensAluno = itens;
		this.itensAluno.forEach(item -> item.setProcesso(this));
	}
	
	@Transient
	public boolean isNovo() {
		return this.getId() == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
