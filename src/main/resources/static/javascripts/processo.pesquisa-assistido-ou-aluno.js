var Npj = Npj || {};

Npj.PesquisaAssistidoOuAluno = (function(){
	
	function PesquisaAssistidoOuAluno() {
		this.assistidoOuAlunoModal = $('#pesquisaProcessosAssistidoOuAlunoModal');
		this.assistidoOuAlunoInput = $('#nomeAssistidoOuAlunoModal');
		this.pesquisaAssistidoOuAlunoBtn = $('.js-pesquisa-assistido-ou-aluno-btn');
		this.htmlTabela = $('#tabela-processos-assistidos-alunos').html();
		this.template = Handlebars.compile(this.htmlTabela);
	}
	
	PesquisaAssistidoOuAluno.prototype.iniciar = function() {
		this.pesquisaAssistidoOuAlunoBtn.on('click', onPesquisaAssistidoOuAlunoClicado.bind(this));
		this.assistidoOuAlunoModal.on('shown.bs.modal', onModalShow.bind(this));
		this.assistidoOuAlunoModal.on('hide.bs.modal', onModalClose.bind(this));
	}
	
	function onModalShow() {
		this.assistidoOuAlunoInput.focus();
	}
	
	function onModalClose() {
		this.assistidoOuAlunoInput.val('');
	}
	
	function onPesquisaAssistidoOuAlunoClicado(evento) {
		evento.preventDefault();
		
		const resposta = $.ajax({
			url: this.assistidoOuAlunoModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: { nomeAssistidoOuAluno : this.assistidoOuAlunoInput.val() }			
		});
		
		resposta.done(onPesquisaAssistidoOuAlunoSucesso.bind(this));
	}
	
	function onPesquisaAssistidoOuAlunoSucesso(resposta) {
		let html = this.template(resposta);
		
		$('#container-tabela-processos-assistidos-alunos').html(html);
		
		$('.js-processo-selecionado-assistido-aluno').on('click', onProcessoSelecionadoClicado.bind(this));
	}
	
	function onProcessoSelecionadoClicado(evento) {
		let elementoSelecionado = $(evento.currentTarget);
		let id = elementoSelecionado.data('id');
		let url = elementoSelecionado.data('url');
		
		const resposta = $.ajax({
			url: `${url}/${id}`,
			method: 'GET'
		});
		
		resposta.done(onConsultaRealizadaSucesso.bind(this));
	}
	
	function onConsultaRealizadaSucesso(resposta) {
		$('.js-linha-time-line').addClass('hidden');
		$('#container-consulta-membros-processo').html(resposta);
		
		this.assistidoOuAlunoModal.modal('hide');
	}
	
	return PesquisaAssistidoOuAluno;
	
}());

$(function(){
	
	let pesquisaAssistidoOuAluno = new Npj.PesquisaAssistidoOuAluno();
	pesquisaAssistidoOuAluno.iniciar();
	
});