package br.com.luciano.npj.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.util.StringUtils;

public class MensagemDTO {

	private Integer id;
	private String foto;
	private String titulo;
	private String conteudo;
	private LocalDate dataInicio;

	public MensagemDTO(String foto, Integer id, String titulo, String conteudo, LocalDate dataInicio) {
		this.foto = foto;
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataInicio = dataInicio;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getFoto() {
		return !StringUtils.isEmpty(foto) ? foto : "pessoa.mock.png";
	}

	public String getTitulo() {
		return titulo;
	}

	public String getConteudo() {
		return !StringUtils.isEmpty(conteudo) && conteudo.length() > 30 ? conteudo.substring(0, 30).concat("...") : conteudo;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataInicio != null ? dataInicio : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}
}
