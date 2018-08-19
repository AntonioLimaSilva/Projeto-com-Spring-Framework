package br.com.luciano.npj.dto;

public class AssistidoMesDTO {

	private String mes;
	private Integer total;

	public AssistidoMesDTO(String mes, Integer total) {
		this.mes = mes;
		this.total = total;
	}

	public String getMes() {
		return mes;
	}

	public Integer getTotal() {
		return total;
	}

}
