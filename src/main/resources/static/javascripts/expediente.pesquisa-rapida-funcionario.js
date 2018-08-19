var Npj = Npj || {};

Npj.PesquisaRapidaFuncionario = (function(){
	
	function PesquisaRapidaFuncionario() {
		this.pesquisaFuncionarioModal = $('#pesquisaFuncionarioModal');
		this.pesquisaFuncionarioBtn = $('.js-pesquisa-funcionario-btn');
		this.funcionarioInput = $('#nomeFuncionarioModal');
		this.htmlTabela = $('#tabela-pesquisa-rapida-funcionario').html();
		this.template = Handlebars.compile(this.htmlTabela);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaFuncionario.prototype.iniciar = function() {
		this.pesquisaFuncionarioBtn.on('click', onPesquisaFuncionarioClicado.bind(this));
		
		this.pesquisaFuncionarioModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaFuncionarioModal.on('hide.bs.modal', onModalHide.bind(this));
	}
	
	function onModalShow() {
		this.funcionarioInput.focus();
	}
	
	function onModalHide() {
		this.funcionarioInput.val('');
		this.mensagemErro.addClass('hidden');
	}
	
	function onPesquisaFuncionarioClicado(evento) {
		evento.preventDefault();
		
		if(this.funcionarioInput.val()) {
			const resposta = $.ajax({
				url: this.pesquisaFuncionarioModal.find('form').attr('action'),
				method: 'GET',
				contentType: 'application/json',
				data: {
					nome: this.funcionarioInput.val()
				}
			});
			
			resposta.done(onPesquisaConcluida.bind(this));
			
			resposta.fail(onErroPesquisa.bind(this));
		} else {
			this.mensagemErro.removeClass('hidden');
		}
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	function onPesquisaConcluida(resposta) {
		let html = this.template(resposta);
		
		$('#containerTabelaPesquisaRapidaFuncionarios').html(html);
		
		$('.js-funcionario-pesquisa-rapida').on('click', onFuncionarioSelecionadoNaLinhaClicado.bind(this));
	}
	
	function onFuncionarioSelecionadoNaLinhaClicado(evento) {
		let linhaSelecionada = $(evento.currentTarget);
		
		$('#idFuncionario').val(linhaSelecionada.data('id'));
		$('#nomeFuncionario').val(linhaSelecionada.data('nome'));
		
		this.pesquisaFuncionarioModal.modal('hide');
	}
	
	return PesquisaRapidaFuncionario;
	
}());

$(function(){
	
	let pesquisaRapidaFuncionario = new Npj.PesquisaRapidaFuncionario();
	pesquisaRapidaFuncionario.iniciar();
	
});