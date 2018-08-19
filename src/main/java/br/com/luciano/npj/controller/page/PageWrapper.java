package br.com.luciano.npj.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20").replaceAll("excluido", "");
		this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getConteudo() {
		return this.page.getContent();
	}
	
	public boolean isVazia() {
		return this.page.getContent().isEmpty();
	}
	
	public int getAtual() {
		return this.page.getNumber();
	}
	
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public int getTamanho(){
		return page.getSize();
	}

	public String urlParaPagina(final int pagina, final int tamanho) {
		return uriBuilder.replaceQueryParam("page", pagina).replaceQueryParam("size", tamanho).build(true).encode().toUriString();
	}
	
	public String urlParaOrdenar(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorOrdenacao = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorOrdenacao).build(true).encode().toUriString();
	}
	
	public String inverterDirecao(String propriedade) {
		String direcao = "asc";
		
		Order ordem = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if(ordem != null) {
			direcao = Sort.Direction.ASC.equals(ordem.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null; 
		
		if (order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

}