var Npj = Npj || {};

Npj.PesquisaRapidaAluno = (function(){
	
	function PesquisaRapidaAluno() {
		this.pesquisaRapidaModal = $('#pesquisaAlunoProcessoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-aluno-processo-btn');
		this.nomeAlunoInput = $('#nomePessoaAlunoModal');
		this.htmlTabela = $('#tabela-pesquisa-rapida-pessoa-processo').html();
		this.template = Handlebars.compile(this.htmlTabela);
		this.uuid = $('#uuid').val();
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaAluno.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaModal.on('shown.bs.modal', onShowModal.bind(this));
		this.pesquisaRapidaModal.on('hide.bs.modal', onCloseModal.bind(this));
		
		bindTabelaItens.call(this);
	}
	
	function onShowModal() {
		this.nomeAlunoInput.focus();
	}
	
	function onCloseModal() {
		this.nomeAlunoInput.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onPesquisaRapidaClicado(evento) {
		evento.preventDefault();
		
		const resposta = $.ajax({
			url: this.pesquisaRapidaModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeAlunoInput.val()
			}
		});
		
		resposta.done(onPesquisaRapidaConcluida.bind(this));
		
		resposta.fail(onErroPesquisa.bind(this));
	}
	
	function onPesquisaRapidaConcluida(resposta) {
		let html = this.template(resposta);
		
		$('#containerTabelaPesquisaRapidaAlunos').html(html);
		
		$('.js-pessoa-selecionada-linha').on('click', onLinhaSelecionadaTabela.bind(this));
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onLinhaSelecionadaTabela(evento) {
		let elementoSelecionado = $(evento.currentTarget);
		
		const resposta = $.ajax({
			url: 'itemAluno',
			method: 'POST',
			data: {
				idAluno: elementoSelecionado.data('id'),
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAssistidoAtualizadoServidor.bind(this));
	}
	
	function onItemAssistidoAtualizadoServidor(resposta) {
		$('.js-tabela-alunos-container').html(resposta);
		
		bindTabelaItens.call(this);
		
		this.pesquisaRapidaModal.modal('hide');
	}
	
	function bindTabelaItens() {		
		$('.js-tabela-item-aluno').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-aluno-btn').on('click', onExcluirAlunoClicado.bind(this));
	}
	
	function onDoubleClick(evento) {
		let value = $(evento.currentTarget);
		value.toggleClass('solicitando-exclusao');
	}
	
	function onExcluirAlunoClicado(evento) {
		let botao = $(evento.target);
		
		let url = botao.data('url-processo');
		let idAluno = botao.data('id-aluno');
		
		const resposta = $.ajax({
			url: `${url}/${this.uuid}/${idAluno}`,
			method: 'DELETE'
		});
		
		resposta.done(onItemAssistidoAtualizadoServidor.bind(this));
	}	
	
	return PesquisaRapidaAluno;
	
}());

$(function(){
	
	let pesquisaRapidaAluno = new Npj.PesquisaRapidaAluno();
	pesquisaRapidaAluno.iniciar();
	
});