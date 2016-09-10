package br.edu.ifpi.eventos.modelo.atividade;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class AtividadeDao extends GenericJPADAO<Atividade>{

	public AtividadeDao() {
		super(Atividade.class);
	}

}
