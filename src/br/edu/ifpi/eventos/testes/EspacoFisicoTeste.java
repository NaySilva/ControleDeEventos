package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class EspacoFisicoTeste {

	@Test
	public void localFiscoComLocaisInternosDentroDeLocaisInternos() {
		EspacoFisico ef = new EspacoFisico("Campus X", TipoEspacoFisico.Campus);
		EspacoFisico ef1 =  new EspacoFisico("Predio A", TipoEspacoFisico.Predio).comLocalExterno(ef);
		EspacoFisico ef11 = new EspacoFisico("Auditorio C", TipoEspacoFisico.Auditorio).comLocalExterno(ef1);
		EspacoFisico ef12 = new EspacoFisico("Sala 3", TipoEspacoFisico.Sala).comLocalExterno(ef1);
		assertEquals(true, ef.getLocaisInternos().contains(ef1) & (ef1.getLocaisInternos().size()==2));
	}
	
	@Test
	public void verificarACapcidadeDoLocal(){
		EspacoFisico ef = new EspacoFisico("Sala 1", TipoEspacoFisico.Sala);
		ef.setCapacidade(2);
		ef.setAtividade(new Atividade("atividade1", null, TipoDeAtividadeEnum.Palestra));
		assertEquals(true, ef.verificarCapacidade());
	}

}
