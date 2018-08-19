package br.com.luciano.npj.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.luciano.npj.model.Aluno;
import br.com.luciano.npj.model.AlunoProcesso;

class TabelaItensAlunoProcesso {
	
	private String uuid;
	private List<AlunoProcesso> itens = new ArrayList<>();
	
	public TabelaItensAlunoProcesso(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Aluno aluno) {
		Optional<AlunoProcesso> itemAlunoOptional = this.buscarItemPorAluno(aluno);
		
		if(!itemAlunoOptional.isPresent()) {
			AlunoProcesso itemAluno = new AlunoProcesso();
			itemAluno.setAluno(aluno);
			
			this.itens.add(itemAluno);
		}
	}
	
	public void excluirItem(Aluno aluno) {
		int indice = IntStream.range(0, this.itens.size())
				.filter(i -> this.itens.get(i).getAluno().equals(aluno)).findAny().getAsInt();
		
		this.itens.remove(indice);
	}

	public String getUuid() {
		return uuid;
	}

	public List<AlunoProcesso> getItens() {
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
		TabelaItensAlunoProcesso other = (TabelaItensAlunoProcesso) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	private Optional<AlunoProcesso> buscarItemPorAluno(Aluno aluno) {
		return this.itens.stream()
				.filter(item -> item.getAluno().equals(aluno))
				.findAny();
	}
}
