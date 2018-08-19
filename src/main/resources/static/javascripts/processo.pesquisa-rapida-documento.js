var Npj = Npj || {};

Npj.PesquisaRapidaDocumento = (function(){
	
	function PesquisaRapidaDocumento() {
		this.pesquisaRapidaModal = $('#pesquisaDocumentoProcessoModal');
		this.nomeDocumentoBtn = $('.js-pesquisa-documento-processo-btn');
		this.nomeDocumentoInput = $('#nomeDocumentoModal');
		this.tabela = $('#tabela-pesquisa-rapida-documento-processo').html();
		this.template = Handlebars.compile(this.tabela);
		this.uuid = $('#uuid').val();
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaDocumento.prototype.iniciar = function() {
		this.nomeDocumentoBtn.on('click', onPesquisarClicado.bind(this));
		this.pesquisaRapidaModal.on('shown.bs.modal', onShowModal.bind(this));
		this.pesquisaRapidaModal.on('hide.bs.modal', onCloseModal.bind(this));
		
		bindTabelaItens.call(this);
	}
	
	function onShowModal() {
		this.nomeDocumentoInput.focus();
	}
	
	function onCloseModal() {
		this.nomeDocumentoInput.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onPesquisarClicado(evento) {
		evento.preventDefault();
		
		const resposta = $.ajax({
			url: this.pesquisaRapidaModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeDocumentoInput.val()
			}
		});
		
		resposta.done(onPesquisaConcluida.bind(this));
		
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onPesquisaConcluida(resultado) {
		let html = this.template(resultado);
		
		$('#containerTabelaPesquisaRapidaDocumentos').html(html);
		
		$('.js-documento-selecionado-linha').on('click', onDocumentoSelecionadoClicado.bind(this));
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onDocumentoSelecionadoClicado(evento) {
		let idDocumento = $(evento.currentTarget).data('id');		
		
		$.ajax({
			url: 'itemDocumento',
			method: 'POST',
			data: {
				idDocumento: idDocumento,
				uuid: this.uuid
			},
			success: onDocumentoAtualizadoServidor.bind(this)
		});
	}
	
	function onDocumentoAtualizadoServidor(resposta) {
		$('.js-tabela-documentos-container').html(resposta);
				
		bindTabelaItens.call(this);
		
		this.pesquisaRapidaModal.modal('hide');
	}
	
	function bindTabelaItens() {		
		$('.js-tabela-item-documento').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-documento-btn').on('click', onExcluirItemClicado.bind(this));
	}
	
	function onDoubleClick(evento) {
		let value = $(evento.currentTarget);
		value.toggleClass('solicitando-exclusao');
	}
	
	function onExcluirItemClicado(evento) {
		let idDocumento = $(evento.target).data('id-documento');
	
		$.ajax({
			url: `itemDocumento/${this.uuid}/${idDocumento}`,
			method: 'DELETE',
			success: onDocumentoAtualizadoServidor.bind(this) 
		});		
	}
	
	return PesquisaRapidaDocumento;
	
}());

$(function(){
	
	let pesquisaRapidaDocumento = new Npj.PesquisaRapidaDocumento();
	pesquisaRapidaDocumento.iniciar();
	
});