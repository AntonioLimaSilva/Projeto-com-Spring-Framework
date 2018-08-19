var Npj = Npj || {};

Npj.ComboEstado = (function(){
	
	function ComboEstado() {
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.iniciar = function() {
		this.combo.on('change', onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboEstado;
	
}());

Npj.ComboCidade = (function(){
	
	function ComboCidade(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenCidadeSelecionada = $('#inputHiddenCidadeSelecionada');
	}
	
	ComboCidade.prototype.iniciar = function() {
		reset.call(this);
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
		let idEstado = this.comboEstado.combo.val();
		inicializarCidades.call(this, idEstado);
	}
	
	function onEstadoAlterado(evento, idEstado) {
		this.inputHiddenCidadeSelecionada.val('');
		inicializarCidades.call(this, idEstado);
	}
	
	function inicializarCidades(codigoEstado) {
		if (codigoEstado) {
			const resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'estado': codigoEstado }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			
			resposta.done(onBuscarCidadesFinalizado.bind(this));
			
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarCidadesFinalizado(cidades) {		
		let options = cidades.map(cidade => `<option value="${cidade.id}">${cidade.nome}</option>`);
		
		this.combo.html(options);
		this.combo.removeAttr('disabled');
		
		let idCidadeSelecionada = this.inputHiddenCidadeSelecionada.val();
		if (idCidadeSelecionada) {
			this.combo.val(idCidadeSelecionada);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione uma cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	return ComboCidade;
	
}());

$(function() {
	
	let comboEstado = new Npj.ComboEstado();
	comboEstado.iniciar();
	
	let comboCidade = new Npj.ComboCidade(comboEstado);
	comboCidade.iniciar();
	
});