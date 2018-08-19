package br.com.luciano.npj.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AgendaDTO {

	private String assistido;
	private String dataInicio;
	private String dataFim;
	private String color;
	
	public AgendaDTO(String assistido, LocalDateTime dataInicio, LocalDateTime dataFim, String color) {
		this.assistido = assistido;
		this.dataInicio = dataInicio != null ? dataInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
		this.dataFim = dataFim != null ? dataFim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
		this.color = color;
	}

	public String getAssistido() {
		return assistido;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}
	
	public String getColor() {
		return color;
	}

}
