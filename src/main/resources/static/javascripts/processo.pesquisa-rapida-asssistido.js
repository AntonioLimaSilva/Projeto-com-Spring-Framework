var Npj = Npj || {};

Npj.AssistidoPesquisaRapida = (function(){
	
	function AssistidoPesquisaRapida() {
		this.pesquisaAssistidoModal = $('#pesquisaAssistidoProcessoModal');
		this.pesquisaAssistidoBtn = $('.js-pesquisa-assistido-processo-btn');
		this.inputNomePessoaAssistido = $('#nomePessoaAssistidoModal');
		this.cantainerTabelaAssistidos = $('#containerTabelaPesquisaRapidaAssistidos');
		this.htmlTabela = $('#tabela-pesquisa-rapida-pessoa-processo').html();
		this.template = Handlebars.compile(this.htmlTabela);
		this.mensagemErro = $('.js-mensagem-erro');
		this.uuid = $('#uuid').val();
		this.tipoParteElement = $('#tipoParteModal');
		this.tipoParteSelecionado = this.tipoParteElement.val();
		this.containerTabelaAssistido = $('.js-tabela-assistidos-container');
	}
	
	AssistidoPesquisaRapida.prototype.iniciar = function() {
		this.pesquisaAssistidoBtn.on('click', onPesquisaRapidaModal.bind(this));
		this.pesquisaAssistidoModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaAssistidoModal.on('hide.bs.modal', onModalHide.bind(this));
		this.tipoParteElement.on('change', onTipoParteSelecionado.bind(this));
		
		bindTabelaItens.call(this);
	}
	
	function onModalShow() {
		this.inputNomePessoaAssistido.focus();
	}
	
	function onModalHide() {
		this.inputNomePessoaAssistido.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onTipoParteSelecionado(evento) {
		this.tipoParteSelecionado = $(evento.target).val();
	}
	
	function onPesquisaRapidaModal(evento) {
		evento.preventDefault();
		
		const resposta = $.ajax({
			url: this.pesquisaAssistidoModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.inputNomePessoaAssistido.val()
			}			
		});
		
		resposta.error(onErroPesquisa.bind(this));		
		resposta.done(onPesquisaConcluida.bind(this));				
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onPesquisaConcluida(resultado) {
		let html = this.template(resultado);
		this.cantainerTabelaAssistidos.html(html);
	
		$('.js-pessoa-selecionada-linha').on('click', onAssistidoSelecionado.bind(this));
	}
	
	function onAssistidoSelecionado(evento) {		
		let assistidoSelecionado = $(evento.currentTarget);
		
		const response = $.ajax({
			url: 'itemAssistido',
			method: 'POST',
			data: {
				idAssistido: assistidoSelecionado.data('id'),
				uuid: this.uuid,
				tipoParte: this.tipoParteSelecionado
			}
		});	
		
		response.done(onItemAssistidoAtualizadoServidor.bind(this));
	}
	
	function onItemAssistidoAtualizadoServidor(resultado) {
		this.containerTabelaAssistido.html(resultado);
		
		bindTabelaItens.call(this);
		
		this.pesquisaAssistidoModal.modal('hide');
	}
	
	function bindTabelaItens() {		
		$('.js-tabela-item-assistido').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-assistido-btn').on('click', onExcluirItemClicado.bind(this));
	}
	
	function onDoubleClick(evento) {
		let value = $(evento.currentTarget);
		value.toggleClass('solicitando-exclusao');	
	}
	
	function onExcluirItemClicado(evento) {
		let botao =  $(evento.target);
		let idAssistido = botao.data('id-assistido');
		let url = botao.data('url-processo');
		
		const resposta = $.ajax({
			url: `${url}/${this.uuid}/${idAssistido}`,
			method: 'DELETE',
		});
		
		resposta.done(onItemAssistidoAtualizadoServidor.bind(this));
	}
			
	return AssistidoPesquisaRapida;
	
}());

$(function(){	
	let assistidoPesquisaRapida = new Npj.AssistidoPesquisaRapida();
	assistidoPesquisaRapida.iniciar();
});