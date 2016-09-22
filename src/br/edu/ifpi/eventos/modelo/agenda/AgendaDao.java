package br.edu.ifpi.eventos.modelo.agenda;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class AgendaDao extends GenericJPADAO<Agenda>{

	public AgendaDao() {
		super(Agenda.class);
	}
	
}
