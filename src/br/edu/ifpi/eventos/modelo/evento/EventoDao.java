package br.edu.ifpi.eventos.modelo.evento;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class EventoDao extends GenericJPADAO<Evento>{

	public EventoDao() {
		super(Evento.class);
	}

}
