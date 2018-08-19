package br.com.luciano.npj.dto;

public class FuncionarioMesDTO {

	private String mes;
	private Integer total;

	public FuncionarioMesDTO(String mes, Integer total) {
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
