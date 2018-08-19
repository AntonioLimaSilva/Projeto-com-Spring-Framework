Npj = Npj || {};

Npj.BotaoSubmit = (function(){
	
	function BotaoSubmit() {
		this.form = $('.js-formulario-principal');
		this.submitBtn = $('.js-submit-btn');
	}
	
	BotaoSubmit.prototype.iniciar = function() {
		this.submitBtn.on('click', onSubmit.bind(this));
	}
	
	function onSubmit(evento) {
		evento.preventDefault();
		
		let botaoClicado = $(evento.target);
		let acao = botaoClicado.data('acao');
		
		let acaoInput = $('<input>');
		acaoInput.attr('name', acao);
		
		this.form.append(acaoInput);
		this.form.submit();
	}
	
	return BotaoSubmit;
	
}());

$(function(){
	
	let botaoSubmit = new Npj.BotaoSubmit();
	botaoSubmit.iniciar();
	
});