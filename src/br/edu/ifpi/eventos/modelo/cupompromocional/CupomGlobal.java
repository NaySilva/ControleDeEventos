package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;

public class CupomGlobal extends CupomPromocional{

	public CupomGlobal(String descricao, Agenda validade, BigDecimal porcentagem) {
		super(descricao, validade, porcentagem);
	}
	

	public BigDecimal valorDoDesconto(Inscricao inscricao) {
		BigDecimal poncentagem = porcentagem.divide(new BigDecimal(100));
		BigDecimal resultado = inscricao.calcularTotalBruto().multiply(poncentagem) ;
		return resultado;
	}

}
