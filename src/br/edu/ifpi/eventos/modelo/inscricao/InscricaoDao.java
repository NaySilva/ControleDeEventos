package br.edu.ifpi.eventos.modelo.inscricao;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class InscricaoDao extends GenericJPADAO<Inscricao>{

	public InscricaoDao() {
		super(Inscricao.class);
	}

}
