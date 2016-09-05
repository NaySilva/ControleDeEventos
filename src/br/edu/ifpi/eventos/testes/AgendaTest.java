package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.util.Agenda;

public class AgendaTest {

	@Test
	public void Verificar_Se_Hoje_Esta_Entre_Um_Periodo() {
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		Agenda setembro = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(0, 0), LocalDate.of(2016, 9, 30), LocalTime.of(23, 59));
		assertEquals(true,	setembro.dentroDoHorario(hoje));		
	}
	
	@Test
	public void Verificar_Se_Hoje_Nao_Esta_Entre_Um_Perido() {
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		Agenda junho = new Agenda(LocalDate.of(2016, 6, 1), LocalTime.of(0, 0), LocalDate.of(2016, 6, 30), LocalTime.of(23, 59));
		assertEquals(false, junho.noMeio(hoje.getDiaFim(), hoje.getHoraFim()));		
	}
	

}
