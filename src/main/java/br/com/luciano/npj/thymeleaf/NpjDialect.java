package br.com.luciano.npj.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.luciano.npj.thymeleaf.processor.ClasseParaErroAtributoTagProcessor;
import br.com.luciano.npj.thymeleaf.processor.MensagemElementTagProcessor;
import br.com.luciano.npj.thymeleaf.processor.MenuAtributoTagProcessor;
import br.com.luciano.npj.thymeleaf.processor.OrdenarElementTagProcessor;
import br.com.luciano.npj.thymeleaf.processor.PaginacaoElementTagProcessor;
import br.com.luciano.npj.thymeleaf.processor.TimeLineElementTagProcessor;

public class NpjDialect extends AbstractProcessorDialect {

	public NpjDialect() {
		super("Nucleo de pratica juridica", "npj", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		
		processadores.add(new MenuAtributoTagProcessor(dialectPrefix));
		processadores.add(new ClasseParaErroAtributoTagProcessor(dialectPrefix));
		processadores.add(new OrdenarElementTagProcessor(dialectPrefix));
		processadores.add(new PaginacaoElementTagProcessor(dialectPrefix));
		processadores.add(new MensagemElementTagProcessor(dialectPrefix));
		processadores.add(new TimeLineElementTagProcessor(dialectPrefix));
		
		return processadores;
	}

	
}
