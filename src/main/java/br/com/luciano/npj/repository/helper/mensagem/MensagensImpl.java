package br.com.luciano.npj.repository.helper.mensagem;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luciano.npj.dto.MensagemConteudoDTO;
import br.com.luciano.npj.dto.MensagemDTO;

public class MensagensImpl implements MensagensQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<MensagemDTO> buscarParaUsuario(Integer idUsuario) {
		String query = "select new br.com.luciano.npj.dto.MensagemDTO(p.foto, m.id, m.titulo, m.conteudo, m.dataInicio) "
				+ "from Mensagem m inner join m.usuarioAtivo ua inner join ua.pessoa p "
				+ "where (m.dataInicio <= DATE(NOW())) and m.dataFim >= DATE(NOW())) and (m.usuarioPassivo.id = :idUsuario "
				+ "or m.usuarioAtivo.id = :idUsuario) order by m.id desc";
		return this.manager.createQuery(query, MensagemDTO.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList();
	}

	@Override
	public Optional<MensagemConteudoDTO> pesquisar(Integer id) {
		String query = "select new br.com.luciano.npj.dto.MensagemConteudoDTO(pPassivo.nome as nomePessoaPassiva, pAtivo.nome as nomePessoaAtiva, m.conteudo) "
				+ "from Mensagem m inner join m.usuarioPassivo up inner join up.pessoa pPassivo "
				+ "inner join m.usuarioAtivo ua inner join ua.pessoa pAtivo where m.id = :id";
		return this.manager.createQuery(query, MensagemConteudoDTO.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findFirst();
	}

	@Override
	public Long totalMensagensValidas(Integer idUsuario) {
		String query = "select count(m.id) from Mensagem m where (m.dataInicio <= DATE(NOW()) and m.dataFim >= DATE(NOW())) "
				+ "and (m.usuarioPassivo.id = :idUsuario or m.usuarioAtivo.id = :idUsuario)";
		Optional<Long> optional = Optional.ofNullable(this.manager.createQuery(query, Long.class)
				.setParameter("idUsuario", idUsuario)
				.getSingleResult());
				return optional.orElse(Long.valueOf(0));
		
	}

}
