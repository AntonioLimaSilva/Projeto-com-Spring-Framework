package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.model.AssistidoProcesso;
import br.com.luciano.npj.model.TipoParte;

@SessionScope
@Component
public class TabelaItensAssistidoSession {

	private List<TabelaItensAssistidoProcesso> tabelas = new ArrayList<>();
	
	public void adicionarItem(String uuid, Assistido assistido, TipoParte tipoParte) {
		TabelaItensAssistidoProcesso tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(assistido, tipoParte);
		tabelas.add(tabela);
	}
	
	public void excluirItem(String uuid, Assistido assistido) {
		TabelaItensAssistidoProcesso tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(assistido);
	}
	
	public List<AssistidoProcesso> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensAssistidoProcesso buscarTabelaPorUuid(String uuid) {
		return tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensAssistidoProcesso(uuid));	
	}
}
