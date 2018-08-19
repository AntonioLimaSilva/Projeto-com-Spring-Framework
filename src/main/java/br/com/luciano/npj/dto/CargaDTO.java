package br.com.luciano.npj.dto;

import java.time.LocalDateTime;

public class CargaDTO {

	private int id;
	private int idProcesso;
	private String nomePessoa;
	private String login;
	private LocalDateTime dataSaida;
	private LocalDateTime dataEntrega;

	public CargaDTO(Integer id, Integer idProcesso, String nomePessoa, String login, LocalDateTime dataSaida,
			LocalDateTime dataEntrega) {
		this.id = id;
		this.idProcesso = idProcesso;
		this.nomePessoa = nomePessoa;
		this.login = login;
		this.dataSaida = dataSaida;
		this.dataEntrega = dataEntrega;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getIdProcesso() {
		return idProcesso;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public String getLogin() {
		return login;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

}
