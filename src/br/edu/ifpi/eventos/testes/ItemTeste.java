package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.AtividadeNaoAptaParaItemException;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;

public class ItemTeste {

	@Test(expected=AtividadeNaoAptaParaItemException.class)
	public void So_Criar_Item_Se_Atividade_For_Pagavel() throws AtividadeNaoAptaParaItemException {
		Atividade at = new AtividadeBuilder().comNome("Palestra").doTipo(TipoDeAtividade.Palestra).naoPagavel().getAtividade();
		Item item = new ItemUnico(new BigDecimal(0), at);
	}

}
