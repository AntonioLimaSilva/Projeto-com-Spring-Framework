package br.com.luciano.npj.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NPJ_REAL_grupo_permissao")
public class GrupoPermissao implements Serializable {

	private static final long serialVersionUID = 1L;

	private GrupoPermissaoId id;

	@EmbeddedId
	public GrupoPermissaoId getId() {
		return id;
	}

	public void setId(GrupoPermissaoId id) {
		this.id = id;
	}

}
