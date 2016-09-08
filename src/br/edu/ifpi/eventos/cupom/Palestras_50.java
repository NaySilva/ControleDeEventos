package br.edu.ifpi.eventos.cupom;

import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Item;
import br.edu.ifpi.eventos.modelo.ItemUnico;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividade;
public class Palestras_50 extends CupomPromocional{

	public Palestras_50(Agenda validade) {
		super(validade);
	}


	@Override
	public double valorDoDesconto(Inscricao inscricao) {
		double resultado = 0.0;
		for (Item item : inscricao.getCarrinho()) {
			if(item instanceof ItemUnico){
				if (((ItemUnico) item).getAtividade().getTipo().equals(TipoDeAtividade.Palestra)){
					resultado += item.getPreco() * 0.5;
				}
			}
		}
		return resultado;
	}

}
