var Npj = Npj || {};

Npj.PesquisaRapidaPessoas = (function(){
	
	function PesquisaRapidaPessoas() {
		this.pesquisaRapidaModal = $('#pesquisaRapidaPessoas');
		this.inputNomePessoa = $('#nomePessoaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pessoas-btn');
		this.mensagemErro = $('.js-mensagem-erro');
		this.templateTabelaPessoa = $('#tabela-pesquisa-rapida-pessoa').html();
		this.template = Handlebars.compile(this.templateTabelaPessoa);
	}
	
	PesquisaRapidaPessoas.prototype.iniciar = function() {
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
			data: {nome: this.inputNomePessoa.val()}
		});
		
		resposta.done(onPesquisaConcluidaSucesso.bind(this));
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onPesquisaConcluidaSucesso(resposta) {
		let html = this.template(resposta);
		
		$('#containerTblPesquisaRapidaPessoas').html(html);
		
		$('.js-pessoa-pesquisa-rapida').on('click', onLinhaPessoaSelecionada.bind(this));
	}
	
	function onLinhaPessoaSelecionada(evento) {
		let elemento = $(evento.currentTarget);
		
		let nomePessoa = elemento.data('nome');
		let idPessoa = elemento.data('id');
		
		$('#nomePessoa').val(nomePessoa);
		$('#idPessoa').val(idPessoa);
		
		this.pesquisaRapidaModal.modal('hide');
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onShowModal() {
		this.inputNomePessoa.focus();
	}
	
	function onCloseModal() {
		this.mensagemErro.addClass('hidden');
	}
	
	return PesquisaRapidaPessoas;
	
}());

$(function(){
	
	let pesquisaRapidaPessoas = new Npj.PesquisaRapidaPessoas();
	pesquisaRapidaPessoas.iniciar();
	
});