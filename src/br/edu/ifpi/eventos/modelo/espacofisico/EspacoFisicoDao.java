package br.edu.ifpi.eventos.modelo.espacofisico;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class EspacoFisicoDao extends GenericJPADAO<EspacoFisico>{

	public EspacoFisicoDao() {
		super(EspacoFisico.class);
	}

}
