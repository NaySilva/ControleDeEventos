package br.edu.ifpi.eventos.modelo.cupompromocional;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;

public class Lote_I extends CupomPromocional{

	public Lote_I(Agenda validade) {
		super(validade);
	}
	

	public double valorDoDesconto(Inscricao inscricao) {
		double poncentagem = 0.50;
		double resultado = inscricao.calcularTotalBruto() * poncentagem ;
		return resultado;
	}

}
