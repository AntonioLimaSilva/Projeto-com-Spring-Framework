package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NPJ_REAL_mensagem")
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String titulo;
	private String conteudo;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Usuario usuarioAtivo;
	private Usuario usuarioPassivo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 50, nullable = false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(nullable = false)
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Column(name = "data_inicio", nullable = false)
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Column(name = "data_fim", nullable = false)
	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario_ativo", foreignKey = @ForeignKey(name = "fk_mensagem_usuario_ativo")
		, nullable = false)
	public Usuario getUsuarioAtivo() {
		return usuarioAtivo;
	}
	
	public void setUsuarioAtivo(Usuario usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_passivo", foreignKey = @ForeignKey(name = "fk_mensagem_usuario_passivo")
		, nullable = false)
	public Usuario getUsuarioPassivo() {
		return usuarioPassivo;
	}
	
	public void setUsuarioPassivo(Usuario usuarioPassivo) {
		this.usuarioPassivo = usuarioPassivo;
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
		Mensagem other = (Mensagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
