package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscri��o;

public class Lote_I implements CupomPromocional{

	@Override
	public double valorDoDesconto(Inscri��o inscri��o) {
		double poncentagem = 0.50;
		double resultado = inscri��o.getEvento().getPreco() * poncentagem ;
		return resultado;
	}

}
