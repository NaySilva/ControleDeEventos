package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeCupom;

public class Lote_I extends CupomPromocional{

	public Lote_I(Agenda validade, TipoDeCupom tipo) {
		super(validade, tipo);
	}
	

	public double valorDoDesconto(Inscrição inscrição) {
		double poncentagem = 0.50;
		double resultado = inscrição.getEvento().getPreco() * poncentagem ;
		return resultado;
	}

}
