package br.com.luciano.npj.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "NPJ_REAL_turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private List<Horario> horarios = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank(message = "Nome deve ser informado")
	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Size(min = 1, message = "Selecione pelo menos um horário")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NPJ_REAL_turma_horario", joinColumns = @JoinColumn(name = "id_turma",
		foreignKey = @ForeignKey(name = "fk_turma_horario_turma"), nullable = false),
			inverseJoinColumns = @JoinColumn(name = "id_horario",
		foreignKey = @ForeignKey(name = "fk_turma_horario_horario"), nullable = false))
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	@Transient
	public boolean isNova() {
		return this.id == null;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.nome);
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
