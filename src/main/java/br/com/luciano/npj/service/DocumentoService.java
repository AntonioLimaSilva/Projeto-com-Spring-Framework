package br.com.luciano.npj.service;

import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.model.Documento;
import br.com.luciano.npj.repository.Documentos;
import br.com.luciano.npj.service.event.documento.DocumentoEvent;
import br.com.luciano.npj.service.exception.NegocioException;
import br.com.luciano.npj.storage.DocumentoStorage;

@Service
public class DocumentoService {
	
	@Autowired
	private Documentos documentos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DocumentoStorage documentoStorage;
	
	@Transactional
	public void salvar(Documento documento) {
		if(StringUtils.isEmpty(documento.getNome())) {
			throw new NegocioException("Faça o upload do arquivo");
		}
		
		if(documento.isNovo()) {
			documento.setDataCriacao(LocalDateTime.now());
		} else {
			Documento documentoExistente = this.documentos.findOne(documento.getId());
			documento.setDataCriacao(documentoExistente.getDataCriacao());
		}
		
		this.publisher.publishEvent(new DocumentoEvent(documento));
		
		this.documentos.save(documento);		
	}

	@Transactional
	public void excluir(Documento documento) {
		try {
			this.documentos.delete(documento);
			this.documentos.flush();
			
			this.documentoStorage.excluirDocumento(documento.getNome());
		} catch(PersistenceException e) {
			throw new NegocioException("Erro excluíndo documento");
		}
	}

}
