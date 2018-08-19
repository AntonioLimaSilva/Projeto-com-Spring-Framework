package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.luciano.npj.model.Acao;
import br.com.luciano.npj.model.AcaoProcesso;

class TabelaItensAcaoProcesso {
	
	private String uuid;
	private List<AcaoProcesso> itens = new ArrayList<>();
	
	public TabelaItensAcaoProcesso(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Acao acao) {
		Optional<AcaoProcesso> itemAcaoOptional = this.buscarItemPorAcao(acao);
		
		if(!itemAcaoOptional.isPresent()) {
			AcaoProcesso itemAcao = new AcaoProcesso();
			itemAcao.setAcao(acao);
			
			itens.add(itemAcao);
		}
	}

	public void excluirItemAcao(Acao acao) {		
		int indice = IntStream.range(0, this.itens.size())
				.filter(i -> this.itens.get(i).getAcao().equals(acao)).findAny().getAsInt();
		
		this.itens.remove(indice);
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public List<AcaoProcesso> getItens() {
		return itens;
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
		TabelaItensAcaoProcesso other = (TabelaItensAcaoProcesso) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	private Optional<AcaoProcesso> buscarItemPorAcao(Acao acao) {
		return this.itens.stream()
				.filter(item -> item.getAcao().equals(acao))
				.findAny();
	}
	
}
