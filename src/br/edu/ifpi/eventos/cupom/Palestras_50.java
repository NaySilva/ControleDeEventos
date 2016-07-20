package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
public class Palestras_50 extends CupomPromocional{

	public Palestras_50(Agenda validade) {
		super(validade);
	}


	@Override
	public double valorDoDesconto(Inscricao inscricao) {
		double resultado = 0.0;
		for (Atividade at : inscricao.getAtividadesDesejadas()) {
			if (at.getTipo().equals(TipoDeAtividadeEnum.Palestra)){
				resultado += at.getPreco() * 0.5;
			}
		}
		return resultado;
	}

}
