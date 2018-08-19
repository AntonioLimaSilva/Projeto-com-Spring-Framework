package br.com.luciano.npj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.model.Cabine;
import br.com.luciano.npj.repository.Cabines;

@Service
public class CabineService {
	
	@Autowired
	private Cabines cabines;
	
	@Transactional
	public void salvar(Cabine cabine) {
		this.cabines.save(cabine);
	}

}
