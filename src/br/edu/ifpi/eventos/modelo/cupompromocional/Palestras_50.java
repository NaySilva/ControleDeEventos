package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
public class Palestras_50 extends CupomPromocional{

	public Palestras_50(Agenda validade) {
		super(validade);
	}


	@Override
	public BigDecimal valorDoDesconto(Inscricao inscricao) {
		BigDecimal resultado = new BigDecimal("0");
		for (Item item : inscricao.getCarrinho()) {
			if(item instanceof ItemUnico){
				if (((ItemUnico) item).getAtividade().getTipo().equals(TipoDeAtividade.Palestra)){
					resultado = resultado.add(item.getPreco().multiply(new BigDecimal("0.5")));
				}
			}
		}
		return resultado;
	}

}
