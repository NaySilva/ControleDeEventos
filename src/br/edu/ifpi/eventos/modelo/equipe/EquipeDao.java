package br.edu.ifpi.eventos.modelo.equipe;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class EquipeDao extends GenericJPADAO<Equipe>{

	public EquipeDao() {
		super(Equipe.class);
	}

}
