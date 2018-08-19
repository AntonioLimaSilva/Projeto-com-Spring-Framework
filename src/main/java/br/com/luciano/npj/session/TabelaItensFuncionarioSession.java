package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.luciano.npj.model.Funcionario;
import br.com.luciano.npj.model.FuncionarioProcesso;
import br.com.luciano.npj.model.TipoParticipacao;

@SessionScope
@Component
public class TabelaItensFuncionarioSession {
	
	private List<TabelaItensFuncionarioProcesso> tabelas = new ArrayList<>();
	
	public void adicionar(String uuid, Funcionario funcionario, TipoParticipacao tipoParticipacao) {
		TabelaItensFuncionarioProcesso tabela = buscarTebelaPorUuid(uuid);
		tabela.adicionar(funcionario, tipoParticipacao);
		tabelas.add(tabela);
	}
	
	
	public List<FuncionarioProcesso> getItens(String uuid) {
		return buscarTebelaPorUuid(uuid).getItens();
	}

	public TabelaItensFuncionarioProcesso buscarTebelaPorUuid(String uuid) {
		return this.tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensFuncionarioProcesso(uuid));
	}

	public void excluirItem(String uuid, Funcionario funcionario) {
		TabelaItensFuncionarioProcesso tabela = buscarTebelaPorUuid(uuid);
		tabela.excluirItem(funcionario);
	}
	
}
