package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.model.DocumentoProcesso;

class TabelaItensDocumentoProcesso {
	
	private String uuid;
	private List<DocumentoProcesso> itens = new ArrayList<>();
	
	public TabelaItensDocumentoProcesso(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Documento documento) {
		Optional<DocumentoProcesso> itemDocumentoOptional = this.buscarItemPorDocumento(documento);
		
		if(!itemDocumentoOptional.isPresent()) {
			DocumentoProcesso itemDocumento = new DocumentoProcesso();
			itemDocumento.setDocumento(documento);
			
			itens.add(itemDocumento);
		}
	}
	
	public void excluirItemDocumento(Documento documento) {
		int indice = IntStream.range(0, this.itens.size())
				.filter(i -> this.itens.get(i).getDocumento().equals(documento)).findAny().getAsInt();
		
		this.itens.remove(indice);
	}

	public String getUuid() {
		return uuid;
	}
	
	public List<DocumentoProcesso> getItens() {
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
		TabelaItensDocumentoProcesso other = (TabelaItensDocumentoProcesso) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	private Optional<DocumentoProcesso> buscarItemPorDocumento(Documento documento) {
		return this.itens.stream()
				.filter(item -> item.getDocumento().equals(documento))
				.findAny();
	}
	
}
