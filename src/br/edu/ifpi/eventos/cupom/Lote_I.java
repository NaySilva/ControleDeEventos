package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscrição;

public class Lote_I implements CupomPromocional{

	@Override
	public double valorDoDesconto(Inscrição inscrição) {
		double poncentagem = 0.50;
		double resultado = inscrição.getEvento().getPreco() * poncentagem ;
		return resultado;
	}

}
