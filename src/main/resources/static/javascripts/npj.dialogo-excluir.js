Npj = Npj || {};

Npj.DialogoExcluir = (function(){
	
	function DialogoExcluir() {
		this.botao = $('.js-excluir-btn');
	}
	
	DialogoExcluir.prototype.iniciar = function() {
		this.botao.on('click', onExcluirClicado.bind(this));
		
		if(window.location.search.indexOf('excluido') > -1) {
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}
	}
	
	function onExcluirClicado(evento) {
		evento.preventDefault();
		
		let botaoClicado = $(evento.currentTarget);		
		let url = botaoClicado.data('url');
		let objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir "' + objeto + '"? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: false,
		}, onExcluirConfirmado.bind(this, url));
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluindo.bind(this)
		});		
	}
	
	function onExcluidoSucesso() {
		let urlAtual = window.location.href;
		let separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		let novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
	
		window.location = novaUrl;
	}
	
	function onErroExcluindo(evento) {
		console.log('Oops! ', evento.responseText, 'error');
		swal('Oops! ', evento.responseText, 'error')
	}
	
	return DialogoExcluir;
	
}());

$(function(){
	let dialogoExcluir = new Npj.DialogoExcluir();
	dialogoExcluir.iniciar();
});