var Npj = Npj || {};

Npj.PesquisarPermissoes = (function(){
	
	function PesquisarPermissoes() {
		this.botaoPermissao = $('.js-permissoes-btn');
		this.modal = $('#pesquisaPermissoesGrupoModal');
		this.htmlTabela = $('#tabela-pesquisa-permissoes-grupo').html();
		this.template = Handlebars.compile(this.htmlTabela);
		this.container = $('.js-conteudo-modal');
	}
	
	PesquisarPermissoes.prototype.iniciar = function() {
		this.botaoPermissao.on('click', onBotaoPermissaoClicado.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
	}
	
	function onModalClose() {
		this.container.empty();
	}
	
	function onBotaoPermissaoClicado(evento) {
		let url = $(evento.currentTarget).data('url-permissoes');
		
		const resposta = $.ajax({
			url: url,
			contentType: 'application/json',
			method: 'GET'
		});
		
		resposta.done(onBuscarPermissoesFinalizado.bind(this));
	}
	
	function onBuscarPermissoesFinalizado(resposta) {
		this.modal.modal('show');	
		
		let html = this.template(resposta);
		this.container.html(html);
	}
	
	return PesquisarPermissoes;
	
}());

$(function(){
	
	let pesquisarPermissoes = new Npj.PesquisarPermissoes();
	pesquisarPermissoes.iniciar();
	
});