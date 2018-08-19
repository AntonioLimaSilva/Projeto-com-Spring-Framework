package br.com.luciano.npj.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;

import br.com.luciano.npj.controller.PessoasController;
import br.com.luciano.npj.controller.converter.AssistidoConverter;
import br.com.luciano.npj.controller.converter.CabineConverter;
import br.com.luciano.npj.controller.converter.CidadeConverter;
import br.com.luciano.npj.controller.converter.DisciplinaConverter;
import br.com.luciano.npj.controller.converter.EquipeConverter;
import br.com.luciano.npj.controller.converter.EstadoConverter;
import br.com.luciano.npj.controller.converter.ExpedienteConverter;
import br.com.luciano.npj.controller.converter.FuncionarioConverter;
import br.com.luciano.npj.controller.converter.GrupoConverter;
import br.com.luciano.npj.controller.converter.PermissaoConverter;
import br.com.luciano.npj.controller.converter.PessoaConverter;
import br.com.luciano.npj.controller.converter.TurmaConverter;
import br.com.luciano.npj.session.TabelaItensAssistidoSession;
import br.com.luciano.npj.thymeleaf.NpjDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = {PessoasController.class, TabelaItensAssistidoSession.class})
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		engine.addDialect(new LayoutDialect());
		engine.addDialect(new DataAttributeDialect());
		engine.addDialect(new NpjDialect());
		engine.addDialect(new SpringSecurityDialect());
		return engine;
	}	
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new CidadeConverter());
		conversionService.addConverter(new EstadoConverter());
		conversionService.addConverter(new PessoaConverter());
		conversionService.addConverter(new GrupoConverter());
		conversionService.addConverter(new DisciplinaConverter());
		conversionService.addConverter(new ExpedienteConverter());
		conversionService.addConverter(new CabineConverter());
		conversionService.addConverter(new EquipeConverter());
		conversionService.addConverter(new FuncionarioConverter());
		conversionService.addConverter(new PermissaoConverter());
		conversionService.addConverter(new TurmaConverter());
		conversionService.addConverter(new AssistidoConverter());
		
		// API de Datas do Java 8
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter() {
		return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8"); // http://www.utf8-chartable.de/
		return bundle;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
	
}
