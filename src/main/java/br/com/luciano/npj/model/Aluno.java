package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "NPJ_REAL_aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String matricula;
	private LocalDateTime dataCriacao;
	private Pessoa pessoa;
	private Cabine cabine;
	private Equipe equipe;
	private Expediente expediente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 20, nullable = false)
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "data_criacao", nullable = false)
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "fk_aluno_pessoa"), nullable = false)
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cabine", foreignKey = @ForeignKey(name = "fk_aluno_cabine"), nullable = false)
	public Cabine getCabine() {
		return cabine;
	}
	
	public void setCabine(Cabine cabine) {
		this.cabine = cabine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_equipe", foreignKey = @ForeignKey(name = "fk_aluno_equipe"), nullable = false)
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_expediente", foreignKey = @ForeignKey(name = "fk_aluno_expediente"), nullable = false)
	public Expediente getExpediente() {
		return expediente;
	}
	
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	@Transient
	public boolean isNovo() {
		return this.id == null;
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
