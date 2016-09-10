package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.Agenda;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscricao;

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
