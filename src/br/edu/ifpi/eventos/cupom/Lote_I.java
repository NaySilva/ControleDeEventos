package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscri��o;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeCupom;

public class Lote_I extends CupomPromocional{

	public Lote_I(Agenda validade, TipoDeCupom tipo) {
		super(validade, tipo);
	}
	

	public double valorDoDesconto(Inscri��o inscri��o) {
		double poncentagem = 0.50;
		double resultado = inscri��o.getEvento().getPreco() * poncentagem ;
		return resultado;
	}

}
