package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.util.Agenda;
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
	public void horarioDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisico("Sala 1", TipoEspacoFisico.Sala);
		Agenda agDoLocal = new Agenda(LocalDate.of(2016, 9, 4),LocalTime.of(0, 0),LocalDate.of(2016, 9, 4),LocalTime.of(23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDate.of(2016, 9, 4), LocalTime.of(8, 0), LocalDate.of(2016,9,4)	, LocalTime.of(12, 0));
		assertEquals(true, ef.disponivelNoHorario(ag));
	}
	
	@Test 
	public void HorarioNaoDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisico("Sala 1", TipoEspacoFisico.Sala);
		Agenda agDoLocal = new Agenda(LocalDate.of(2016, 9, 4),LocalTime.of(0, 0),LocalDate.of(2016, 9, 4),LocalTime.of(23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDate.of(2016, 9, 5), LocalTime.of(8, 0), LocalDate.of(2016,9,5), LocalTime.of(12, 0));
		assertEquals(false, ef.disponivelNoHorario(ag));
	}

}
