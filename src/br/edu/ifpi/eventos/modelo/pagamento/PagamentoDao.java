package br.edu.ifpi.eventos.modelo.pagamento;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class PagamentoDao extends GenericJPADAO<Pagamento>{

	public PagamentoDao() {
		super(Pagamento.class);
	}

}
