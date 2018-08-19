package br.com.luciano.npj.repository.helper.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.npj.dto.ProcessoComAssistidoAlunoDTO;
import br.com.luciano.npj.model.Processo;

public class ProcessosImpl implements ProcessosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional(readOnly = true)
	@Override
	public Processo buscarProcessoPor(Integer id) {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Processo.class);
		
		criteria.add(Restrictions.eq("id", id));
				
		return (Processo) criteria.uniqueResult();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Processo buscarUltimoProcesso() {
		Criteria criteria = this.manager.unwrap(Session.class).createCriteria(Processo.class);

		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
				
		return (Processo) criteria.uniqueResult();
	}
	
	@Override
	public List<ProcessoComAssistidoAlunoDTO> buscarProcessoComAssistidoAluno(String nomeAssistidoOuAluno) {
		return this.manager.createQuery("select distinct NEW br.com.luciano.npj.dto.ProcessoComAssistidoAlunoDTO(processo.id, p1.nome as assistido, p2.nome as aluno) "
				+ "from Processo processo "
				+ "left join AssistidoProcesso assistidoProcesso on assistidoProcesso.processo.id = processo.id "
				+ "left join Assistido assistido on assistido.id = assistidoProcesso.assistido.id "
				+ "left join Pessoa p1 on p1.id = assistido.pessoa.id "
				+ "left join AlunoProcesso alunoProcesso on alunoProcesso.processo.id = processo.id "
				+ "left join Aluno aluno on aluno.id = alunoProcesso.aluno.id "
				+ "left join Pessoa p2 on p2.id = aluno.pessoa.id "				
				+ "where lower(p1.nome) like lower(:nomeAssistidoOuAluno) or lower(p2.nome) like lower(:nomeAssistidoOuAluno) order by processo.id desc", ProcessoComAssistidoAlunoDTO.class)
				.setParameter("nomeAssistidoOuAluno", "%" + nomeAssistidoOuAluno + "%")
				.getResultList();
	}
	
}
