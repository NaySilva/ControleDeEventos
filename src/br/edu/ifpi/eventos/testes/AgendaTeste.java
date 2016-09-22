package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;

public class AgendaTeste {
	

	@Test
	public void Hoje_Esta_Entre_O_Mes_De_Setembro() {
		Agenda setembro = new Agenda(LocalDateTime.of(2016, 9, 1, 0, 0), LocalDateTime.of(2016, 9, 30, 23, 59));
		assertEquals(true,	setembro.dentroDoHorario(Agenda.noMomento));		
	}
	
	@Test
	public void Hoje_Nao_Esta_Entre_O_Mes_De_Junho() {
		Agenda junho = new Agenda(LocalDateTime.of(2016, 6, 1, 0, 0), LocalDateTime.of(2016, 6, 30, 23, 59));
		assertEquals(false, junho.dentroDoHorario(Agenda.noMomento));		
	}
	
	@Test
	public void Ordenar_Duas_Agendas(){
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 29, 14, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 29, 12, 0));
		assertEquals(true, ag1.compareAgendaTo(ag2)>0);
	}
	
	@Test
	public void Agenda_Interfere_Em_Outra_Agenda(){
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 29, 12, 0));
		assertEquals(false, ag1.semInterferenciaNoHorario(ag2));
	}
	
	@Test 
	public void Agenda_Nao_Interfere_Na_Outra_Agenda(){
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 29, 14, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 29, 12, 0));
		assertEquals(true, ag1.semInterferenciaNoHorario(ag2));
	}
	

}
