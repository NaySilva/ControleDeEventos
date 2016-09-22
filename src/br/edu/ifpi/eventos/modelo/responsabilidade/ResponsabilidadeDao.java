package br.edu.ifpi.eventos.modelo.responsabilidade;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class ResponsabilidadeDao extends GenericJPADAO<Responsabilidade>{

	public ResponsabilidadeDao() {
		super(Responsabilidade.class);
	}

}
