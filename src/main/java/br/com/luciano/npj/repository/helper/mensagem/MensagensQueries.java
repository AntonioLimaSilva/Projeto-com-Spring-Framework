package br.com.luciano.npj.repository.helper.mensagem;

import java.util.List;
import java.util.Optional;

import br.com.luciano.npj.dto.MensagemConteudoDTO;
import br.com.luciano.npj.dto.MensagemDTO;

public interface MensagensQueries {
	
	List<MensagemDTO> buscarParaUsuario(Integer idUsuario);
	
	Long totalMensagensValidas(Integer idUsuario);
	
	Optional<MensagemConteudoDTO> pesquisar(Integer id);

}
