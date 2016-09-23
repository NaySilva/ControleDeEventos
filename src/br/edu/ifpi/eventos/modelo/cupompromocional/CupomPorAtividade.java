package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;

import br.edu.ifpi.eventos.enums.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
public class CupomPorAtividade extends CupomPromocional{
	//Esse tipo especifica qual o tipo de atividade esse cupom vale
	private TipoDeAtividade tipo;

	public CupomPorAtividade(String descricao, Agenda validade, BigDecimal porcentagem, TipoDeAtividade tipo) {
		super(descricao, validade, porcentagem);
		this.tipo = tipo;
	}

	@Override
	public BigDecimal valorDoDesconto(Inscricao inscricao) {
		BigDecimal resultado = new BigDecimal(0);
		for (Item item : inscricao.getCarrinho()) {
			if(item instanceof ItemUnico){
				if (((ItemUnico) item).getAtividade().getTipo().equals(tipo)){
					resultado = resultado.add(item.getPreco().multiply(porcentagem.divide(new BigDecimal(100))));
				}
			}
		}
		return resultado;
	}
}
