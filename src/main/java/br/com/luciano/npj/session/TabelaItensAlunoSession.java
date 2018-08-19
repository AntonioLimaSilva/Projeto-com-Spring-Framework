package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.model.AlunoProcesso;

@SessionScope
@Component
public class TabelaItensAlunoSession {

	private List<TabelaItensAlunoProcesso> tabelas = new ArrayList<>();
	
	public void adicionarItem(String uuid, Aluno aluno) {
		TabelaItensAlunoProcesso tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(aluno);
		
		this.tabelas.add(tabela);
	}
	
	public void excluirItemAluno(String uuid, Aluno aluno) {
		TabelaItensAlunoProcesso tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(aluno);	
	}
	
	public List<AlunoProcesso> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensAlunoProcesso buscarTabelaPorUuid(String uuid) {
		return this.tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensAlunoProcesso(uuid));
	}

}
