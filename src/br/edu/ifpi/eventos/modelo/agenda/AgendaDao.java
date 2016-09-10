package br.edu.ifpi.eventos.modelo.agenda;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class AgendaDao extends GenericJPADAO<Agenda>{

	public AgendaDao() {
		super(Agenda.class);
	}
	
}
