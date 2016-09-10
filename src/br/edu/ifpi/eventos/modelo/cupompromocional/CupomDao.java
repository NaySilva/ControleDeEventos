package br.edu.ifpi.eventos.modelo.cupompromocional;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class CupomDao extends GenericJPADAO<CupomPromocional>{

	public CupomDao() {
		super(CupomPromocional.class);
	}

}
