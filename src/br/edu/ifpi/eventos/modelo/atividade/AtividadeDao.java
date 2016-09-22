package br.edu.ifpi.eventos.modelo.atividade;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;
import br.edu.ifpi.eventos.modelo.evento.Evento;

@Repository
public class AtividadeDao extends GenericJPADAO<Atividade>{

	public AtividadeDao() {
		super(Atividade.class);
	}

	public Evento acharEvento(Long id) {
		TypedQuery<Evento> query = manager.createQuery("from evento where id = :paramId", Evento.class);
		query.setParameter("paramId", id);
		return query.getSingleResult();
	}

}
