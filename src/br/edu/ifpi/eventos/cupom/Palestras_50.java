package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Palestra;

public class Palestras_50 implements CupomPromocional{

	@Override
	public double valorDoDesconto(Inscrição inscricao) {
		double resultado = 0.0;
		for (Atividade at : inscricao.getAtividadesDesejadas()) {
			if (at instanceof Palestra){
				resultado += at.getPreco() * 0.5;
			}
		}
		return resultado;
	}

}
