package br.com.luciano.npj.repository.helper.usuario;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.luciano.npj.dto.UsuarioDTO;
import br.com.luciano.npj.model.Usuario;
import br.com.luciano.npj.repository.filter.UsuarioFilter;
import br.com.luciano.npj.repository.paginacao.PaginacaoUtil;

public class UsuariosImpl implements UsuariosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Usuario.class);
		
		this.paginacaoUtil.preparar(criteria, pageable);
		
		this.adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public Optional<Usuario> buscarPorLoginAtivo(String login) {
		return this.manager.createQuery("select u from Usuario u inner join fetch u.pessoa p where lower(login) = lower(:login) and ativo = true", Usuario.class)
				.setParameter("login", login)
				.getResultList()
				.stream()
				.findFirst();
	}
	
	@Override
	public List<String> buscarPermissoes(Usuario usuario) {
		String query = "select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario";
		return this.manager.createQuery(query, String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}
	
	@Override
	public Optional<Usuario> buscarPorId(Integer id) {
		String query = "select distinct u from Usuario u inner join fetch u.pessoa p inner join fetch u.grupos g where u.id = :id";
		return this.manager.createQuery(query, Usuario.class)
				.setParameter("id", id)
				.getResultList()
				.stream()
				.findAny();
	}
	
	@Override
	public List<UsuarioDTO> buscarUsuariosAtivosPorNome(String nome) {
		String query = "select new br.com.luciano.npj.dto.UsuarioDTO(u.id, p.nome, p.pseudonimo, p.cpf) from Usuario u "
				+ "left join u.pessoa p where u.ativo = :ativo and upper(p.nome) like upper(:nome)";
		return this.manager.createQuery(query, UsuarioDTO.class)
				.setParameter("ativo", Boolean.TRUE)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

	private Long total(UsuarioFilter filtro) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Usuario.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {
		criteria.setFetchMode("pessoa", FetchMode.JOIN);
		if(filtro != null) {
			if(filtro.getPessoa() != null && !StringUtils.isEmpty(filtro.getPessoa().getNome())) {
				criteria.createAlias("pessoa", "p");
				criteria.add(Restrictions.ilike("p.nome", filtro.getPessoa().getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getLogin())) {
				criteria.add(Restrictions.ilike("login", filtro.getLogin(), MatchMode.START));
			}
			
		}
	}

	
}
