package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.AtividadeNaoAptaParaItemException;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;

public class ItemTeste {

	@Test(expected=AtividadeNaoAptaParaItemException.class)
	public void So_Criar_Item_Se_Atividade_For_Pagavel() throws AtividadeNaoAptaParaItemException {
		Atividade at = new Atividade("Palestra", TipoDeAtividade.Palestra);
		at.setPagavel(false);
		Item item = new ItemUnico(0, at);
	}

}
