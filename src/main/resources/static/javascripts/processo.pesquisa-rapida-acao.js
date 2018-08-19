var Npj = Npj || {};

Npj.PesquisaRapidaAcao = (function(){
	
	function PesquisaRapidaAcao() {
		this.pesquisaRapidaAcaoModal = $('#pesquisaAcaoProcessoModal');
		this.nomeAcaoElement = $('#nomeAcaoModal');
		this.uuid = $('#uuid').val();
	}
	
	PesquisaRapidaAcao.prototype.iniciar = function() {
		this.nomeAcaoElement.on('change', onPesquisaRapidaClicado.bind(this));
		
		bindTabelaItens.call(this);
	}
	
	function onPesquisaRapidaClicado(evento) {
		let idAcaoSelecionado = $(evento.target).val();
		
		if(idAcaoSelecionado >= 1) {
			const resposta = $.ajax({
				url: 'itemAcao',
				method: 'POST',
				data: {
					idAcao: idAcaoSelecionado,
					uuid: this.uuid
				}
			});
			
			resposta.done(onItemAtualizadoServidor.bind(this, idAcaoSelecionado));
		}
	}
	
	function onItemAtualizadoServidor(idAcao, resposta) {
		if(idAcao >= 1) {
			$('.js-tabela-acoes-container').html(resposta);
			
			this.pesquisaRapidaAcaoModal.modal('hide');
			this.nomeAcaoElement.val('');
			
			bindTabelaItens.call(this);
		}
	}
	
	function bindTabelaItens() {		
		$('.js-tabela-item-acao').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-acao-btn').on('click', onExcluirItemClicado.bind(this));
	}
	
	function onDoubleClick(evento) {
		let value = $(evento.currentTarget);
		value.toggleClass('solicitando-exclusao');
	}
	
	function onExcluirItemClicado(evento) {
		let idAcao = $(evento.target).data('id-acao');
		
		const resposta = $.ajax({
			url: `itemAcao/${this.uuid}/${idAcao}`,
			method: 'DELETE',
		});
		
		resposta.done(onItemAtualizadoServidor.bind(this, idAcao));
	}
		
	return PesquisaRapidaAcao;
	
}());

$(function(){
	
	let pesquisaRapidaAcao = new Npj.PesquisaRapidaAcao();
	pesquisaRapidaAcao.iniciar();
	
});