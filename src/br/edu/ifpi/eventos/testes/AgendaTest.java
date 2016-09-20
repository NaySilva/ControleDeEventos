package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;

public class AgendaTest {

	@Test
	public void Verificar_Se_Hoje_Esta_Entre_Um_Periodo() {
		Agenda setembro = new Agenda(LocalDateTime.of(2016, 9, 1, 0, 0), LocalDateTime.of(2016, 9, 30, 23, 59));
		assertEquals(true,	setembro.dentroDoHorario(Agenda.noMomento));		
	}
	
	@Test
	public void Verificar_Se_Hoje_Nao_Esta_Entre_Um_Perido() {
		Agenda junho = new Agenda(LocalDateTime.of(2016, 6, 1, 0, 0), LocalDateTime.of(2016, 6, 30, 23, 59));
		assertEquals(false, junho.dentroDoHorario(Agenda.noMomento));		
	}
	
	@Test
	public void Ordenar_Duas_Agendas(){
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 29, 14, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 29, 12, 0));
		assertEquals(true, ag1.compareAgendaTo(ag2)>0);
	}
	

}
