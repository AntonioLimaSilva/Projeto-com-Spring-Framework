package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GLOBAL_pessoa")
@DynamicUpdate
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String pseudonimo;
	private String email;
	private String foneResidencial;
	private String foneCelular;
	private Sexo sexo = Sexo.MASCULINO;
	private String rg;
	private String cpf;
	private LocalDate dataNascimento;
	private String naturalidade;
	private String nacionalidade;
	private String profissao;
	private EstadoCivil estadoCivil;
	private Boolean idoso;
	private Boolean portadorDeficiencia;
	private Boolean atendimentoPreferencial;
	private String observacao;
	private LocalDateTime dataCriacao;
	private String foto;
	private String contentType;
	private Endereco endereco;
	private boolean isNovaFoto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false)
	public String getPseudonimo() {
		return pseudonimo;
	}

	public void setPseudonimo(String pseudonimo) {
		this.pseudonimo = pseudonimo;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fone_residencial")
	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	@Column(name = "fone_celular", nullable = false)
	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "data_nascimento")
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_civil", nullable = false)
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@Column(nullable = false)
	public Boolean getIdoso() {
		return idoso;
	}

	public void setIdoso(Boolean idoso) {
		this.idoso = idoso;
	}

	@Column(name = "portador_deficiencia", nullable = false)
	public Boolean getPortadorDeficiencia() {
		return portadorDeficiencia;
	}

	public void setPortadorDeficiencia(Boolean portadorDeficiencia) {
		this.portadorDeficiencia = portadorDeficiencia;
	}

	@Column(name = "atendimento_preferencial", nullable = false)
	public Boolean getAtendimentoPreferencial() {
		return atendimentoPreferencial;
	}

	public void setAtendimentoPreferencial(Boolean atendimentoPreferencial) {
		this.atendimentoPreferencial = atendimentoPreferencial;
	}

	@Column(length = 600)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "data_criacao", nullable = false)
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Transient
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "pessoa.mock.png";
	}

	@Column(name = "content_type")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * O endereço não pode ser LAZER, pelo fato de ser preciso excluir os elementos orfãos, caso 
	 * a anotação fetch seja habilitada para LAZER, os elementos orfãos não serão excluídos
	 * 
	 * @return Endereço
	 */
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_endereco", foreignKey = @ForeignKey(name = "fk_pessoa_endereco"), nullable = false)
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Transient
	public boolean isNova() {
		return this.id == null;
	}
	
	@Transient
	public boolean isNovaFoto() {
		return isNovaFoto;
	}

	public void setNovaFoto(boolean isNovaFoto) {
		this.isNovaFoto = isNovaFoto;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}