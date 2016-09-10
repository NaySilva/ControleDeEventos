package br.edu.ifpi.eventos.modelo.pagamento;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class PagamentoDao extends GenericJPADAO<Pagamento>{

	public PagamentoDao() {
		super(Pagamento.class);
	}

}
