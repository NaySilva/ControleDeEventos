package br.edu.ifpi.eventos.modelo.inscricao;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class InscricaoDao extends GenericJPADAO<Inscricao>{

	public InscricaoDao() {
		super(Inscricao.class);
	}

}
