package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;

public class EspacoFisicoTeste {

	@Test
	public void local_Fisico_Com_Locais_Internos_Dentro_DeLocais_Internos() {
		EspacoFisico ef = new EspacoFisico("Campus X", TipoEspacoFisico.Campus);
		EspacoFisico ef1 =  new EspacoFisico("Predio A", TipoEspacoFisico.Predio).comLocalExterno(ef);
		EspacoFisico ef11 = new EspacoFisico("Auditorio C", TipoEspacoFisico.Auditorio).comLocalExterno(ef1);
		EspacoFisico ef12 = new EspacoFisico("Sala 3", TipoEspacoFisico.Sala).comLocalExterno(ef1);
		assertEquals(true, ef.getLocaisInternos().contains(ef1) & (ef1.getLocaisInternos().size()==2));
	}
	
	@Test
	public void horarioDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisico("Sala 1", TipoEspacoFisico.Sala);
		Agenda agDoLocal = new Agenda(LocalDateTime.of(2016, 9, 4, 0, 0), LocalDateTime.of(2016, 9, 4, 23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDateTime.of(2016, 9, 4, 8, 0), LocalDateTime.of(2016, 9, 4, 12, 0));
		assertEquals(true, ef.disponivelNoHorario(ag));
	}
	
	@Test 
	public void HorarioNaoDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisico("Sala 1", TipoEspacoFisico.Sala);
		Agenda agDoLocal = new Agenda(LocalDateTime.of(2016, 9, 4, 0, 0), LocalDateTime.of(2016, 9, 4, 23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDateTime.of(2016, 9, 5, 8, 0), LocalDateTime.of(2016, 9, 5, 12, 0));
		assertEquals(false, ef.disponivelNoHorario(ag));
	}

}
