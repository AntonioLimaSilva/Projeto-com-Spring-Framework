package br.com.luciano.npj.model;

import java.io.Serializable;
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
@Table(name = "NPJ_REAL_grupo")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private List<Permissao> permissoes;

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

	@Size(min = 1, message = "Selecione pelo menos uma permissão")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NPJ_REAL_grupo_permissao", joinColumns = @JoinColumn(name = "id_grupo", 
			foreignKey = @ForeignKey(name = "fk_grupo_permissao_grupo"), nullable = false),
		inverseJoinColumns = @JoinColumn(name = "id_permissao", 
			foreignKey = @ForeignKey(name = "fk_grupo_permissao_permissao"), nullable = false))
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
