package br.com.luciano.npj.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class TimeLineElementTagProcessor extends AbstractElementTagProcessor {
	
	private static final String NOME_TAG = "timeLine";
	private static final int PRECEDENCIA = 1000;

	public TimeLineElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		IAttribute attrProcesso = tag.getAttribute("attrProcesso");
		IAttribute attrItensAssistido = tag.getAttribute("attrItensAssistido");
		IAttribute attrItensFuncionario = tag.getAttribute("attrItensFuncionario");
		IAttribute attrItensDocumento = tag.getAttribute("attrItensDocumento");
		IAttribute attrItensAcao = tag.getAttribute("attrItensAcao");
		IAttribute attrItensAluno = tag.getAttribute("attrItensAluno");
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block", 
				"th:replace", String.format("fragments/TimeLineProcesso :: timeLine (%s,%s,%s,%s,%s,%s)",
						attrProcesso.getValue(), attrItensAssistido.getValue(), attrItensFuncionario.getValue(),
						attrItensDocumento.getValue(), attrItensAcao.getValue(), attrItensAluno.getValue())));
		
		structureHandler.replaceWith(model, true);
		
	}

}
