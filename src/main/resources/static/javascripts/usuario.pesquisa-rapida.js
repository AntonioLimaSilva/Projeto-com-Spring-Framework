var Npj = Npj || {};

Npj.PesquisaRapida = (function(){
	
	function PesquisaRapida() {
		this.pesquisaRapidaModal = $('#pesquisaRapidaUsuarios');		
		this.inputNome = $('#nomeUsuarioPassivoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pessoas-btn');
		this.mensagemErro = $('.js-mensagem-erro');
		this.containerTabela = $('#containerTabelaPesquisaRapidaUsuarios');
		this.htmlTabela = $('#tabela-pesquisa-rapida-pessoa').html();
		this.template = Handlebars.compile(this.htmlTabela);
	}
	
	PesquisaRapida.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		
		this.pesquisaRapidaModal.on('shown.bs.modal', onModalShow.bind(this));		
	}
	
	function onModalShow() {
		this.inputNome.focus();
	}
	
	function onPesquisaRapidaClicado(evento) {
		evento.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.inputNome.val()
			},
		
			success: onPesquisaConcluida.bind(this),
			error: onMensagemErroValidacao.bind(this)
		});
	}	
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		let html = this.template(resultado);
		this.containerTabela.html(html);
		
		let tabelaPessoaPesquisaRapida = new Npj.TabelaPessoaPesquisaRapida(this.pesquisaRapidaModal);
		tabelaPessoaPesquisaRapida.iniciar();
	}
	
	function onMensagemErroValidacao() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapida;
	
}());

Npj.TabelaPessoaPesquisaRapida = (function(){
	
	function TabelaPessoaPesquisaRapida(modal) {
		this.modalPessoa = modal;
		this.pessoa = $('.js-pessoa-pesquisa-rapida');
	}
	
	TabelaPessoaPesquisaRapida.prototype.iniciar = function() {
		this.pessoa.on('click', onPessoaSelecionada.bind(this));
	}
	
	function onPessoaSelecionada(evento) {
		this.modalPessoa.modal('hide');
		
		let pessoaSelecionada = $(evento.currentTarget);
		$('#nomeUsuario').val(pessoaSelecionada.data('nome'));
		$('#idUsuario').val(pessoaSelecionada.data('id'));
	}
	
	return TabelaPessoaPesquisaRapida;
	
}());

$(function(){
	
	let pesquisaRapida = new Npj.PesquisaRapida();
	pesquisaRapida.iniciar();
	
});