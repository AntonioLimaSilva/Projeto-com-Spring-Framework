package br.com.luciano.npj.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.luciano.npj.model.Acao;
import br.com.luciano.npj.model.AcaoProcesso;

@SessionScope
@Component
public class TabelaItensAcaoSession {
	
	private Set<TabelaItensAcaoProcesso> tabelas = new HashSet<>();
	
	public void adicionarItem(String uuid, Acao acao) {
		TabelaItensAcaoProcesso tabela = buscarItemPorUuid(uuid);
		tabela.adicionarItem(acao);
		
		tabelas.add(tabela);
	}
	
	public void excluirItemAcao(String uuid, Acao acao) {
		TabelaItensAcaoProcesso tabela = buscarItemPorUuid(uuid);
		tabela.excluirItemAcao(acao);
	}
	
	public List<AcaoProcesso> getItens(String uuid) {
		return buscarItemPorUuid(uuid).getItens();
	}
	
	private TabelaItensAcaoProcesso buscarItemPorUuid(String uuid) {
		return this.tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensAcaoProcesso(uuid));
	}

}
