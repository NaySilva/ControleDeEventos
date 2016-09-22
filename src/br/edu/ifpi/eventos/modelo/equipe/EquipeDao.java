package br.edu.ifpi.eventos.modelo.equipe;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class EquipeDao extends GenericJPADAO<Equipe>{

	public EquipeDao() {
		super(Equipe.class);
	}

}
