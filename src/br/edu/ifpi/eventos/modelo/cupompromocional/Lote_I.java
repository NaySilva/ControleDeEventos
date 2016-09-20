package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;

public class Lote_I extends CupomPromocional{

	public Lote_I(Agenda validade) {
		super(validade);
	}
	

	public BigDecimal valorDoDesconto(Inscricao inscricao) {
		BigDecimal poncentagem = new BigDecimal("0.5");
		BigDecimal resultado = inscricao.calcularTotalBruto().multiply(poncentagem) ;
		return resultado;
	}

}
