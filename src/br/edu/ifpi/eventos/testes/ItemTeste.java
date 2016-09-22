package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.edu.ifpi.eventos.enums.TipoDeAtividade;
import br.edu.ifpi.eventos.excecoes.AtividadeNaoAptaParaItemException;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemComposto;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;

public class ItemTeste {
	Item itemSimples;

	@Test(expected=AtividadeNaoAptaParaItemException.class)
	public void So_Criar_Item_Se_Atividade_For_Pagavel() throws AtividadeNaoAptaParaItemException {
		Atividade at = new AtividadeBuilder().comNome("Palestra").doTipo(TipoDeAtividade.Palestra).naoPagavel().getAtividade();
		itemSimples = new ItemUnico(new BigDecimal(0), at);
	}
	
	@Test
	public void Criar_Item_Composto_Com_Valor_Unico() throws AtividadeNaoAptaParaItemException{
		Atividade at1 = new AtividadeBuilder().comNome("Palestra1").doTipo(TipoDeAtividade.Palestra).pagavel().getAtividade();
		Atividade at2 = new AtividadeBuilder().comNome("Palestra2").doTipo(TipoDeAtividade.Palestra).pagavel().getAtividade();
		Item item = new ItemComposto(new BigDecimal(100));
		((ItemComposto) item).adicionarItemUnico(new ItemUnico(new BigDecimal(60), at1));
		((ItemComposto) item).adicionarItemUnico(new ItemUnico(new BigDecimal(70), at2));
		assertEquals(((ItemComposto) item).getItens().size(), 2);
	}

}
