package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.model.DocumentoProcesso;

@SessionScope
@Component
public class TabelaItensDocumentoSession {
	
	private List<TabelaItensDocumentoProcesso> tabelas = new ArrayList<>();
	
	public void adicionarItem(String uuid, Documento documento) {
		TabelaItensDocumentoProcesso tabela = buscarItemPorUuid(uuid);
		tabela.adicionarItem(documento);
		
		tabelas.add(tabela);
	}
	
	public void excluirItemDocumento(String uuid, Documento documento) {
		TabelaItensDocumentoProcesso tabela = buscarItemPorUuid(uuid);
		tabela.excluirItemDocumento(documento);
	}
	
	public List<DocumentoProcesso> getItens(String uuid) {
		return buscarItemPorUuid(uuid).getItens();
	}
	
	private TabelaItensDocumentoProcesso buscarItemPorUuid(String uuid) {
		return this.tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensDocumentoProcesso(uuid));
	}


}
