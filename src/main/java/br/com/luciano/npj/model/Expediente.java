package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "NPJ_REAL_expediente")
@DynamicUpdate
public class Expediente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String periodoLetivo;
	private Boolean ativo = Boolean.TRUE;
	private LocalDateTime dataCriacao;
	private Funcionario funcionario;
	private Disciplina disciplina;
	private List<Turma> turmas = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "periodo_letivo", length = 20, nullable = false)
	public String getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(String periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	@Column(nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Column(name = "data_criacao", nullable = false)
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "fk_expediente_funcionario")
		, nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_disciplina", foreignKey = @ForeignKey(name = "fk_expediente_disciplina")
		, nullable = false)
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NPJ_REAL_expediente_turma", joinColumns = @JoinColumn(name = "id_expediente", 
			foreignKey = @ForeignKey(name = "fk_expediente_turma_expediente"), nullable = false),
		inverseJoinColumns = @JoinColumn(name = "id_turma",
			foreignKey = @ForeignKey(name = "fk_expediente_turma_turma"), nullable = false))
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
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
		Expediente other = (Expediente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
