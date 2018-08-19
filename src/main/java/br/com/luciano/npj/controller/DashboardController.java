package br.com.luciano.npj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.luciano.npj.repository.Assistidos;
import br.com.luciano.npj.repository.Funcionarios;
import br.com.luciano.npj.repository.Pessoas;
import br.com.luciano.npj.repository.Reus;

@Controller
public class DashboardController {
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private Assistidos assistidos;
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private Reus reus;
	
	@RequestMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("totalPessoas", pessoas.count());
		mv.addObject("totalAssistidos", assistidos.count());
		mv.addObject("totalFuncionarios", funcionarios.count());
		mv.addObject("totalReus", reus.count());

		return mv;
	}

}
