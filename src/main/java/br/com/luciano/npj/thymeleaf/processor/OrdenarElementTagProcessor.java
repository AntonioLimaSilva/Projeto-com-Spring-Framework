package br.com.luciano.npj.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class OrdenarElementTagProcessor extends AbstractElementTagProcessor {
	
	private static final String NOME_TAG = "ordernar";
	private static final int PRECEDENCIA = 1000;

	public OrdenarElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);		
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute pagina = tag.getAttribute("page");
		IAttribute campo = tag.getAttribute("field");
		IAttribute texto = tag.getAttribute("text");
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("fragments/Ordenacao :: order (%s, %s, %s)", pagina.getValue(), campo.getValue(), texto.getValue())));
		
		structureHandler.replaceWith(model, true);
		
	}

	

}
