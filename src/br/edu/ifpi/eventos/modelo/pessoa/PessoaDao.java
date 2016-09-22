package br.edu.ifpi.eventos.modelo.pessoa;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class PessoaDao extends GenericJPADAO<Pessoa>{

	public PessoaDao() {
		super(Pessoa.class);
	}

}
