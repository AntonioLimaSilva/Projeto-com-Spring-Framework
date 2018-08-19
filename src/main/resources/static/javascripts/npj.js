var Npj = Npj || {};

Npj.TotalMensagens = (function(){
	
	function TotalMensagens() {
		this.totalMensagensSpan = $('.js-total-span');
		this.totalMensagensDropdown = $('.js-total-dropdown');
	}
	
	TotalMensagens.prototype.iniciar = function() {
		$.ajax({
			url: this.totalMensagensSpan.data('url-total'),
			method: 'GET',
			contentType: 'application/json',
			success: onTotalFinalizado.bind(this)
		});
	}
	
	function onTotalFinalizado(total) {
		this.totalMensagensSpan.html(total);
		this.totalMensagensDropdown.html(`Você tem ${total} mensagens`);
	}
	
	return TotalMensagens;
}());

Npj.Mensagens = (function(){
	
	function Mensagens() {
		this.linkMensagem = $('#link-mensagem');
		this.containerMensagem = $('#container-menu-mensagens');
		this.htmlTabela = $('#tabela-pesquisa-mensagens').html();
		this.template = Handlebars.compile(this.htmlTabela);
	}
	
	Mensagens.prototype.iniciar = function() {
		this.linkMensagem.on('click', onLinkMensagemClicada.bind(this));
	}
	
	function onLinkMensagemClicada(evento) {		
		$.ajax({
			url: this.containerMensagem.data('url'),
			method: 'GET',
			contentType: 'application/json',
			success: onMensagensFinalizado.bind(this)
		});		
	}
	
	function onMensagensFinalizado(resposta) {		
		let html = this.template({resposta: resposta});
		this.containerMensagem.html(html);
		
		$('.js-link-mensagem-selecionada').on('click', onMensagemClicada.bind(this));
	}
	
	function onMensagemClicada(evento) {
		evento.preventDefault();
		
		let elemento = $(evento.currentTarget);
		let idMensagem = elemento.find('input').attr('value');
		let url = elemento.find('input').data('url');
		
		$.ajax({
			url: `${url}/${idMensagem}`,
			method: 'GET',
			contentType: 'application/json',
			success: onConteudoMensagemFizalidado.bind(this)
		});
	}
	
	function onConteudoMensagemFizalidado(resposta) {		
		let conteudo = resposta.conteudo;
		let pessoaAtiva =  resposta.nomePessoaAtiva;
		let pessoaPassiva = resposta.nomePessoaPassiva;
		
		$('.js-conteudo-mensagem-modal').html(`<span>${conteudo}</span><br/></br><strong>Atenciosamente: </strong>${pessoaAtiva}`);
		$('.js-nome-pessoa-mensagem').html(`<strong>Para: </strong>${pessoaPassiva}`);
		
		$('#conteudoMensagemModal').modal('show');
	}
	
	return Mensagens;
}());

Npj.DatePicker = (function(){
	
	function DatePicker() {
		 this.dataPicker = $('.js-datetime')
	}
	
	DatePicker.prototype.iniciar = function() {
		this.dataPicker.datetimepicker({
			language: 'pt-BR',
		    format: 'dd/mm/yyyy hh:ii',
		    showTodayButton: true,
		    sideBySide: true,
		    showClose: true,
		    showClear: true,
		    toolbarPlacement: 'top',
		    autoclose: true,
		    linkFormat: 'dd/MM/yyy HH:mm'
		});
	}
	
	return DatePicker;	
}());

Npj.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Npj.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

Npj.MaskCpf = (function(){
	
	function MaskCpf() {
		this.inputCpf = $('.js-cpf');
	}
	
	MaskCpf.prototype.enable = function() {
		this.inputCpf.mask('000.000.000-00');
	}
	
	return MaskCpf;
	
}());

Npj.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
	}
	
	return MaskDate;
	
}());

Npj.MaskDateTime = (function() {
	
	function MaskDateTime() {
		this.inputDate = $('.js-date-time');
	}
	
	MaskDateTime.prototype.enable = function() {
		this.inputDate.mask('00/00/0000 00:00:00');
	}
	
	return MaskDateTime;
	
}());

Npj.Security = (function(){
	
	function Security() {
		this.header = $('input[name=_csrf_header]').val();
		this.token = $('input[name=_csrf]').val();
	}
	
	Security.prototype.iniciar = function() {
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

$(function() {
	let totalMensagens = new Npj.TotalMensagens();
	totalMensagens.iniciar();
	
	let mensagens = new Npj.Mensagens();
	mensagens.iniciar();
	
	let datePicker = new Npj.DatePicker();
	datePicker.iniciar();
	
	let maskDateTime = new Npj.MaskDateTime();
	maskDateTime.enable();
	
	let maskPhoneNumber = new Npj.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	let maskCep = new Npj.MaskCep();
	maskCep.enable();
	
	let maskCpf = new Npj.MaskCpf();
	maskCpf.enable();
	
	let maskDate = new Npj.MaskDate();
	maskDate.enable();	
	
	let security = new Npj.Security();
	security.iniciar();
});
