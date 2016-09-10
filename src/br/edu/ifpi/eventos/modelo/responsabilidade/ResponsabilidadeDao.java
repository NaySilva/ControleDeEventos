package br.edu.ifpi.eventos.modelo.responsabilidade;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class ResponsabilidadeDao extends GenericJPADAO<Responsabilidade>{

	public ResponsabilidadeDao() {
		super(Responsabilidade.class);
	}

}
