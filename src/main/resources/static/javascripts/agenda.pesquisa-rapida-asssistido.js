var Npj = Npj || {};

Npj.PesquisaRapidaAssistido = (function(){
	
	function PesquisaRapidaAssistido() {
		this.modalAssistido = $('#pesquisaAssistidoModal');
		this.inputNomeAssistido = $('#nomeAssistidoModal');
		this.buttonPesquisar = $('.js-pesquisa-assistido-btn');
		this.tabelaAssistido = $('#tabela-pesquisa-rapida-pessoa-processo').html();
		this.template = Handlebars.compile(this.tabelaAssistido);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaAssistido.prototype.iniciar = function() {
		this.buttonPesquisar.on('click', onPesquisarAssistido.bind(this));
		this.modalAssistido.on('shown.bs.modal', onModalShow.bind(this));
		this.modalAssistido.on('hide.bs.modal', onModalHide.bind(this));
	}
	
	function onPesquisarAssistido(evento) {
		evento.preventDefault();
		
		let nomeAssistido = this.inputNomeAssistido.val();
		
		const resposta = $.ajax({
			url: this.modalAssistido.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {nome: nomeAssistido},
		});
		
		resposta.done(onPesquisaAssistidoConcluida.bind(this));
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onPesquisaAssistidoConcluida(resposta) {
		let html = this.template(resposta);
		
		$('#containerTabelaPesquisaRapidaAssistidos').html(html);
		
		$('.js-pessoa-selecionada-linha').on('click', onAssistidoSelecionado.bind(this));
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onAssistidoSelecionado(evento) {
		let elemento = $(evento.currentTarget);
		
		nomeAssistido = elemento.data('nome');
		idAssistido = elemento.data('id');
		
		$('#nomeAssistido').val(nomeAssistido);
		$('#idAssistido').val(idAssistido);
		
		this.modalAssistido.modal('hide');
	}
	
	function onModalShow() {
		this.inputNomeAssistido.focus();
	}
	
	function onModalHide() {
		this.inputNomeAssistido.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	return PesquisaRapidaAssistido;
	
}());

$(function(){
	
	let pesquisaRapidaAssistido = new Npj.PesquisaRapidaAssistido();
	pesquisaRapidaAssistido.iniciar();
	
});