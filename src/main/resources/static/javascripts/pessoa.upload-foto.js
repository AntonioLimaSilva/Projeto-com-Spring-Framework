var Npj = Npj || {};

Npj.UploadFoto = (function(){
	
	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		this.isNovaFoto = $('input[name=novaFoto]');
		
		this.htmlFotoPessoaTemplate = $('#foto-pessoa').html();
		this.template = Handlebars.compile(this.htmlFotoPessoaTemplate);
		
		this.containerFotoPessoa = $('.js-container-foto-pessoa');
		
		this.uploadDrop = $('#upload-drop');	
	}
	
	UploadFoto.prototype.iniciar = function() {
		const settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoPessoa.data('url-fotos'),
			complete: onUploadCompleto.bind(this),
			beforeSend: adiconarCsrfToken.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if(this.inputNomeFoto.val()) {
			renderizarFoto.call(this, {nomeFoto: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}	
	}
		
	function onUploadCompleto(resposta) {
		this.isNovaFoto.val('true');
		renderizarFoto.call(this, resposta);
	}
	
	function renderizarFoto(resposta) {
		this.inputNomeFoto.val(resposta.nomeFoto);
		this.inputContentType.val(resposta.contentType);
		
		let foto = '';
		if(this.isNovaFoto.val() == 'true') {
			foto = 'temp/';
		}
		
		foto += resposta.nomeFoto;
		
		this.uploadDrop.addClass('hidden');	
		var htmlFotoPessoa = this.template({foto: foto});
		this.containerFotoPessoa.append(htmlFotoPessoa);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));	
	}
	
	function onRemoverFoto() {
		$('.js-foto-pessoa').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
		this.isNovaFoto.val('false');		
	}
	
	function adiconarCsrfToken(xhr) {
		let header = $('input[name=_csrf_header]').val();
		let token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}
	
	return UploadFoto;
	
})();

$(function(){
	
	let uploadFoto = new Npj.UploadFoto();
	uploadFoto.iniciar();
	
});