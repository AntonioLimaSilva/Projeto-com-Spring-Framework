var Npj = Npj || {};

Npj.PesquisaRapidaFuncionario = (function(){
	
	function PesquisaRapidaFuncionario() {
		this.modal = $('#pesquisaFuncionarioProcessoModal');
		this.nomeFuncionarioInput = $('#nomePessoaFuncionarioModal');
		this.tipoParticipacaoElement = $('#tipoParticipacaoModal');
		this.tipoParticipacaoSelecionado = this.tipoParticipacaoElement.val();
		this.pesquisaFuncionarioBtn = $('.js-pesquisa-funcionario-processo-btn');
		this.htmlTabela = $('#tabela-pesquisa-rapida-pessoa-processo').html();
		this.template = Handlebars.compile(this.htmlTabela);
		this.uuid = $('#uuid').val();
		this.tabelaFuncionarioContainer = $('.js-tabela-funcionarios-container');
		this.tipoParticipacaoElement = $('#tipoParticipacaoModal');
		this.tipoParticipacaoSelecionado = this.tipoParticipacaoElement.val();
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaFuncionario.prototype.iniciar = function() {
		this.pesquisaFuncionarioBtn.on('click', onPesquisaRapidaModal.bind(this));
		this.modal.on('shown.bs.modal', onShowModal.bind(this));
		this.modal.on('hide.bs.modal', onCloseModal.bind(this));
		this.tipoParticipacaoElement.on('change', onTipoParticipacaoSelecionado.bind(this));
		
		bindTabelaItens.call(this);
	}
	
	function onTipoParticipacaoSelecionado(evento) {
		this.tipoParticipacaoSelecionado = $(evento.target).val();
	}
	
	function onCloseModal() {
		this.nomeFuncionarioInput.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onShowModal() {
		this.nomeFuncionarioInput.focus();
	}
	
	function onPesquisaRapidaModal(evento) {
		evento.preventDefault();
		
		var resposta = $.ajax({
			url: this.modal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeFuncionarioInput.val()
			}			
		});
		
		resposta.done(onPesquisaConcluida.bind(this));	
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onPesquisaConcluida(funcionarios) {
		let html = this.template(funcionarios);
		
		$('#containerTabelaPesquisaRapidaFuncionarios').html(html);
		
		$('.js-pessoa-selecionada-linha').on('click', onFuncionarioClicado.bind(this));
	}
	
	function onFuncionarioClicado(evento) {
		let funcionarioSelecionado = $(evento.currentTarget);	
		
		const resposta = $.ajax({
			url: 'itemFuncionario',
			method: 'POST',
			data: {
				idFuncionario: funcionarioSelecionado.data('id'),
				uuid: this.uuid,
				tipoParticipacao: this.tipoParticipacaoSelecionado
			}
		});
	
		resposta.done(onItemFuncionarioAtualizadoServidor.bind(this));
	}
	
	function onItemFuncionarioAtualizadoServidor(resposta) {
		this.tabelaFuncionarioContainer.html(resposta);
		
		bindTabelaItens.call(this);
		
		this.modal.modal('hide');
	}
	
	function bindTabelaItens() {		
		$('.js-tabela-item-funcionario').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-funcionario-btn').on('click', onExcluirItemClicado.bind(this));
	}
	
	function onDoubleClick(evento) {
		let value = $(evento.currentTarget);
		value.toggleClass('solicitando-exclusao');
	}
	
	function onExcluirItemClicado(evento) {
		let botao = $(evento.target);
		let idFuncionario = botao.data('id-funcionario');
		let url = botao.data('url-processo');
		
		$.ajax({
			url: `${url}/${this.uuid}/${idFuncionario}`,
			method: 'DELETE',
			success: onItemFuncionarioAtualizadoServidor.bind(this)
		});
	}	
	
	return PesquisaRapidaFuncionario;
	
}());

$(function(){
	let pesquisaRapidaFuncionario = new Npj.PesquisaRapidaFuncionario();
	pesquisaRapidaFuncionario.iniciar();
});