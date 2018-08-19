package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.model.TipoParticipacao;

class TabelaItensFuncionarioProcesso {

	private String uuid;
	private List<FuncionarioProcesso> itens = new ArrayList<>();

	public TabelaItensFuncionarioProcesso(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionar(Funcionario funcionario, TipoParticipacao tipoParticipacao) {
		Optional<FuncionarioProcesso> itemFuncionarioOptional = buscarItemPorFuncionario(funcionario);
		
		FuncionarioProcesso itemFuncionario = null;
		if(itemFuncionarioOptional.isPresent()) {
			itemFuncionario = buscarItemPorFuncionario(funcionario).get();
			itemFuncionario.setTipoParticipacao(tipoParticipacao);
		} else {
			itemFuncionario = new FuncionarioProcesso();
			itemFuncionario.setFuncionario(funcionario);
			itemFuncionario.setTipoParticipacao(tipoParticipacao);
			
			this.itens.add(itemFuncionario);
		}
	}
	
	public void excluirItem(Funcionario funcionario) {
		int indice = IntStream.range(0, this.itens.size())
				.filter(i -> this.itens.get(i).getFuncionario().equals(funcionario)).findAny().getAsInt();
		
		this.itens.remove(indice);
	}
	
	public List<FuncionarioProcesso> getItens() {
		return itens;
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		TabelaItensFuncionarioProcesso other = (TabelaItensFuncionarioProcesso) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	private Optional<FuncionarioProcesso> buscarItemPorFuncionario(Funcionario funcionario) {
		return this.itens.stream().filter(item -> item.getFuncionario().equals(funcionario)).findAny();
	}


}
