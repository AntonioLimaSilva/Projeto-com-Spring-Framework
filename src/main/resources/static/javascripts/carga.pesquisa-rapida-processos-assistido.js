var Npj = Npj || {};

Npj.PesquisaRapidaProcessosAssistido = (function(){
	
	function PesquisaRapidaProcessosAssistido() {
		this.pesquisaRapidaModal = $('#pesquisaRapidaAssistodoProcessos');
		this.inputNomeAssistido = $('#nomeAssistidoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pessoa-processos-btn');
		this.mensagemErro = $('.js-mensagem-erro');
		this.tabelaAssistidoProcessos = $('#tabela-processos-assistido').html();
		this.template = Handlebars.compile(this.tabelaAssistidoProcessos);
	}
	
	PesquisaRapidaProcessosAssistido.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaModal.on('shown.bs.modal', onShowModal.bind(this));
		this.pesquisaRapidaModal.on('hide.bs.modal', onCloseModal.bind(this));
	}
	
	function onPesquisaRapidaClicado(evento) {
		evento.preventDefault();
		
		const resposta = $.ajax({
			url: this.pesquisaRapidaModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {nome: this.inputNomeAssistido.val()}
		});
		
		resposta.done(onPesquisaConcluidaSucesso.bind(this));
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onPesquisaConcluidaSucesso(resposta) {
		let html = this.template(resposta);
		$('#containerTblPesquisaRapidaProcessosAsssistido').html(html);
		
		$('.js-processo-selecionado-assistido').on('click', onLinhaSelecionadaAssistodoProcesso.bind(this));
	}
	
	function onLinhaSelecionadaAssistodoProcesso(evento) {
		let idProcesso = $(evento.currentTarget).data('id');
		
		$('#idProcessoOnly').val(idProcesso);
		$('#idProcesso').val(idProcesso);
		
		this.pesquisaRapidaModal.modal('hide');
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onShowModal() {
		this.inputNomeAssistido.focus();
	}
	
	function onCloseModal() {
		this.mensagemErro.addClass('hidden');
	}
	
	return PesquisaRapidaProcessosAssistido;
	
}());

$(function(){
	
	let pesquisaRapidaProcessosAssistido = new Npj.PesquisaRapidaProcessosAssistido();
	pesquisaRapidaProcessosAssistido.iniciar();
	
});