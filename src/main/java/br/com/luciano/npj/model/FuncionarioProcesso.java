package br.com.luciano.npj.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "NPJ_REAL_funcionario_processo")
public class FuncionarioProcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Funcionario funcionario;
	private Processo processo;
	private TipoParticipacao tipoParticipacao;
	private LocalDateTime dataCriacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "fk_funcionario_processo_funcionario")
		, nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_processo", foreignKey = @ForeignKey(name = "fk_funcionario_processo_processo")
		, nullable = false)
	public Processo getProcesso() {
		return processo;
	}
	
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_participacao", length = 100, nullable = false)
	public TipoParticipacao getTipoParticipacao() {
		return tipoParticipacao;
	}
	
	public void setTipoParticipacao(TipoParticipacao tipoParticipacao) {
		this.tipoParticipacao = tipoParticipacao;
	}	

	@Column(name = "data_criacao", nullable = false)
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@PrePersist
	public void salvarDataCriacao() {
		this.dataCriacao = LocalDateTime.now();
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
		FuncionarioProcesso other = (FuncionarioProcesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
