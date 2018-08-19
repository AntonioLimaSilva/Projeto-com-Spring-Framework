var Npj = Npj || {};

Npj.Agenda = (function(){
	
	function Agenda() {
		this.agenda = $('#agenda');
		this.buttonSalvar = $('.js-salvar-agenda-btn');
		this.inputAssistido = $('#cadAssistido');
		this.inputDataInicio = $('#dataInicio');
		this.inputDataFim = $('#dataFim');
		this.optionColor = $('#color');
		this.cadAgendaModal = $('#cadAgendaModal');
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	Agenda.prototype.iniciar = function() {
		this.agenda.fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: moment(),
			editable: true,
			eventLimit: true,
			lang:'pt-br',
			buttonText: {
			    today: 'Hoje',
			    month: 'Mês',
			    week: 'Semana',
			    day: 'Dia'
			},
			monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
			dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabado'],
			dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
			eventClick: onAgendamentoClicado.bind(this),
			selectable: true,
			selectHelper: true,
			select: onAgendamentoSelecionado.bind(this), 
			events: onBuscarAgendamentos.bind(this)
		});
				
		this.buttonSalvar.on('click', onSalvarAgendamento.bind(this));
		this.cadAgendaModal.on('hide.bs.modal', onModalHide.bind(this));
		this.cadAgendaModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onAgendamentoClicado(evento) {
		let assistido = evento.title;
		let dataInicio = evento.start.format('DD/MM/YYYY HH:mm');
		let dataFim = evento.end != null ? evento.end.format('DD/MM/YYYY HH:mm') : dataInicio;
		
		$('#visAgendaModal #assistido').text(assistido);
		$('#visAgendaModal #start').text(dataInicio);
		$('#visAgendaModal #end').text(dataFim);
		$('#visAgendaModal').modal('show');
		
		return false;
	}
	
	function onAgendamentoSelecionado(start, end) {
		if(start.isAfter(moment())) {
			this.inputDataInicio.val(moment(start).format('DD/MM/YYYY HH:mm'));
			this.inputDataFim.val(moment(end).subtract(1, 'day').format('DD/MM/YYYY HH:mm'));
			this.cadAgendaModal.modal('show');
		}
	}
	
	function onBuscarAgendamentos(start, end, timezone, callback) {
		$.ajax({
			url: 'agendas',
			success: function(dados) {
				let agendamentos = [];
				
				dados.forEach(dado => {
					agendamentos.push({
						title: dado.assistido,
						start: dado.dataInicio,
						end: dado.dataFim,
						color: dado.color
					});
				});
				
				callback(agendamentos);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(`${textStatus} -  ${errorThrown}`);
			}
		});
	}
	
	function onSalvarAgendamento(evento) {
		evento.preventDefault();
		
		let assistido = this.inputAssistido.val();
		let dataInicio = this.inputDataInicio.val();
		let dataFim = this.inputDataFim.val();
		let color = this.optionColor.val();
		
		$.ajax({
			url: this.cadAgendaModal.find('form').attr('action'),
			method: 'POST',
			data: { assistido, dataInicio, dataFim, color },
			success: onAgendamentoSalvoSucesso.bind(this),
			error: onErroSalvando.bind(this)
		});
	}
	
	function onAgendamentoSalvoSucesso(resposta) {
		this.cadAgendaModal.modal('hide');
		
		window.location.reload();
	}
	
	function onErroSalvando(error) {
		let messages = error.responseJSON;
		
		let message = messages.map(m => `<span>${m}</span><br>`);
		
		this.mensagemErro.html(message);
		this.mensagemErro.removeClass('hidden');
	}
	
	function onModalShow() {
		this.inputAssistido.focus();
	}
	
	function onModalHide() {
		this.mensagemErro.addClass('hidden');		
	}
	
	return Agenda;
	
}());

$(function(){
	
	let agenda = new Npj.Agenda();
	agenda.iniciar();
	
});