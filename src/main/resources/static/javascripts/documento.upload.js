var Npj = Npj || {};

Npj.UploadDocumento = (function(){
	
	function UploadDocumento() {
		this.inputNomeDocumento = $('input[name=nome]');
		this.inputContentType = $('input[name=contentType]');
		this.inputTamanho = $('input[name=tamanho]');
		this.documentoUpload = $('#upload-drop');					
		this.documentoThumbnail = $('.js-documento');
		this.containerDocumento = $('.js-container-documento');
	}
	
	UploadDocumento.prototype.iniciar = function() {
		const settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(doc|docx|pdf)',
				action: this.containerDocumento.data('url-documentos'),
				complete: onUploadCompleto.bind(this),
				beforeSend: adiconarCsrfToken.bind(this)
		};
		
		if(this.inputNomeDocumento.val()) {
			onUploadCompleto.call(this, { nomeDocumento:  this.inputNomeDocumento.val(), contentType: this.inputContentType.val(), tamanho: this.inputTamanho.val()});
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.documentoUpload, settings);
	}
	
	function onUploadCompleto(resposta) {
		this.inputNomeDocumento.val(resposta.nomeDocumento);
		this.inputContentType.val(resposta.contentType);
		this.inputTamanho.val(resposta.tamanho);
		
		this.documentoUpload.addClass('hidden');
		this.documentoThumbnail.removeClass('hidden');
		
		$('.js-remove-documento').on('click', onRemoveDocumento.bind(this));			
		
	}
	
	function onRemoveDocumento() {
		this.documentoUpload.removeClass('hidden');
		this.documentoThumbnail.addClass('hidden');
		this.inputNomeDocumento.val('');
		this.inputContentType.val('');
		this.inputTamanho.val('');
	}
	
	function adiconarCsrfToken(xhr) {
		let header = $('input[name=_csrf_header]').val();
		let token = $('input[name=_csrf]').val();
		
		xhr.setRequestHeader(header, token);
	}
	
	return UploadDocumento;
	
}());

$(function(){
	
	let uploadDocumento = new Npj.UploadDocumento();
	uploadDocumento.iniciar();
	
});