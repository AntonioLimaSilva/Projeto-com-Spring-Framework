package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.model.TipoParte;

class TabelaItensAssistidoProcesso {

	private String uuid;
	private List<AssistidoProcesso> itens = new ArrayList<>();

	public TabelaItensAssistidoProcesso(String uuid) {
		this.uuid = uuid;
	}

	public void adicionarItem(Assistido assistido, TipoParte tipoParte) {
		Optional<AssistidoProcesso> itemAssistidoOptional = buscarItemPorAssistido(assistido);
		
		AssistidoProcesso itemAssistido = null;
		if(itemAssistidoOptional.isPresent()) {
			itemAssistido = buscarItemPorAssistido(assistido).get();
			itemAssistido.setTipoParte(tipoParte);
		} else {
			itemAssistido = new AssistidoProcesso();
			itemAssistido.setAssistido(assistido);
			itemAssistido.setTipoParte(tipoParte);
			
			this.itens.add(itemAssistido);
		}
	}

	public void excluirItem(Assistido assistido) {		
		int indice = IntStream.range(0, this.itens.size())
				.filter(i -> this.itens.get(i).getAssistido().equals(assistido)).findAny().getAsInt();
		
		this.itens.remove(indice);
	}

	public String getUuid() {
		return uuid;
	}
	
	public List<AssistidoProcesso> getItens() {
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
		TabelaItensAssistidoProcesso other = (TabelaItensAssistidoProcesso) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	private Optional<AssistidoProcesso> buscarItemPorAssistido(Assistido assistido) {
		return this.itens.stream().filter(item -> item.getAssistido().equals(assistido)).findAny();
	}
	
}
