package br.com.luciano.npj.repository.helper.assistido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.dto.AssistidoDTO;
import br.com.luciano.npj.dto.AssistidoMesDTO;
import br.com.luciano.npj.model.Assistido;
import br.com.luciano.npj.repository.filter.AssistidoFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class AssistidosImpl implements AssistidosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public Page<Assistido> filtrar(AssistidoFilter filtro, Pageable pageable) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Assistido.class);
		
		this.paginacaoUtil.preparar(criteria, pageable);
		this.adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<AssistidoDTO> buscarPessoaAssitidoPorNome(String nome) {
		String query = "select new br.com.luciano.npj.dto.AssistidoDTO(a.id, p.nome, p.pseudonimo, p.cpf) from Assistido a "
				+ "left join Pessoa p on p.id = a.pessoa.id where lower(p.nome) like lower(:nome)";
		
		return this.manager.createQuery(query, AssistidoDTO.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	
	@Override
	public Optional<Assistido> buscarPorId(Integer id) {
		String query = "select distinct a from Assistido a inner join fetch a.pessoa p where a.id = :id";
		return this.manager.createQuery(query, Assistido.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}
	
	@Override
	public List<AssistidoMesDTO> buscarTotalMes() {
		List<AssistidoMesDTO> resultList = this.manager.createNamedQuery("Assistidos.totalPorMes", AssistidoMesDTO.class).getResultList();
		
		LocalDate now = LocalDate.now();
		for (int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", now.getYear(), now.getMonth().getValue());
			
			boolean possuiMes = resultList.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if (!possuiMes) {
				resultList.add(i - 1, new AssistidoMesDTO(mesIdeal, 0));
			}
			
			now = now.minusMonths(1);
		}
		
		return resultList;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Assistido buscarAssistidoComPessoaPorId(Integer idAssistido) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Assistido.class);
		criteria.createAlias("pessoa", "p", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", idAssistido));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Assistido) criteria.uniqueResult();
	}

	private Long total(AssistidoFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Assistido.class);
		this.adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(AssistidoFilter filtro, Criteria criteria) {
		criteria.setFetchMode("pessoa", FetchMode.JOIN);
		criteria.createAlias("pessoa", "p");
		
		if(filtro != null) {
			if(filtro.getPessoa() != null && !StringUtils.isEmpty(filtro.getPessoa().getNome())) {
				criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
			}
		}
	}

}
