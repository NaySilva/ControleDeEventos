package br.edu.ifpi.eventos.modelo.cupompromocional;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class CupomDao extends GenericJPADAO<CupomPromocional>{

	public CupomDao() {
		super(CupomPromocional.class);
	}

}
