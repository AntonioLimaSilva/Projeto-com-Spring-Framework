var Npj = Npj || {};

Npj.GraficoTotalPessoasAtendidaNoMes = (function(){
	
	function GraficoTotalPessoasAtendidaNoMes() {
		this.ctx = $('#graficoAssistidoNoMes')[0].getContext('2d');
	}
	
	GraficoTotalPessoasAtendidaNoMes.prototype.iniciar = function() {	
		$.ajax({
			url: 'assistidos/quantidadeAssistidoNoMes',
			method: 'GET',
			success: onDadosRecebidos.bind(this)
		});		
	}
	
	function onDadosRecebidos(dados) {
		let meses = [];
		let valores = [];
		
		dados.forEach(obj => {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoVendasPorMes = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: meses,
				datasets: [{
					label: 'Assistidos atendido no mês',
					backgroundColor: "rgba(26,179,148,0.5)",
					pointBorderColor: "rgba(26,179,148,1)",
					pointBackgroundColor: "#fff",
					data: valores
				}]
			},
		});
	}
	
	return GraficoTotalPessoasAtendidaNoMes;
	
}());

Npj.GraficoTotalProcessosAndamentoNoMes = (function(){
	
	function GraficoTotalProcessosAndamentoNoMes() {
		this.ctx = $('#graficoProcessosAtendidoNoMes')[0].getContext('2d');
	}
	
	GraficoTotalProcessosAndamentoNoMes.prototype.iniciar = function() {
		$.ajax({
			url: 'funcionarios/totalProcessosNoMes',
			method: 'GET',
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(dados) {
		let meses = [];
		let valores = [];
		
		dados.forEach(obj => {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoVendasPorMes = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: meses,
				datasets: [{
					label: 'Processos atendido no mês',
					backgroundColor: "rgba(26,179,148,0.5)",
					pointBorderColor: "rgba(26,179,148,1)",
					pointBackgroundColor: "#fff",
					data: valores
				}]
			},
		});
	}
	
	return GraficoTotalProcessosAndamentoNoMes;
	
}());

$(function(){
	
	let graficoTotalPessoasAtendidaNoMes = new Npj.GraficoTotalPessoasAtendidaNoMes();
	graficoTotalPessoasAtendidaNoMes.iniciar();
	
	let graficoTotalProcessosAndamentoNoMes = new Npj.GraficoTotalProcessosAndamentoNoMes();
	graficoTotalProcessosAndamentoNoMes.iniciar();
	
});