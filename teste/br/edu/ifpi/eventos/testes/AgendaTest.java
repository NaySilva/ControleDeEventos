package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.util.Agenda;

public class AgendaTest {

	@Test
	public void Verificar_Se_Hoje_Esta_Entre_Um_Perido() {
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		Agenda julho = new Agenda(LocalDate.of(2016, 7, 1), LocalTime.of(0, 0), LocalDate.of(2016, 7, 30), LocalTime.of(23, 59));
		assertEquals(true, julho.noMeio(hoje.getDiaFim(), hoje.getHoraFim()));		
	}
	
	@Test
	public void Verificar_Se_Hoje_Nao_Esta_Entre_Um_Perido() {
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		Agenda junho = new Agenda(LocalDate.of(2016, 6, 1), LocalTime.of(0, 0), LocalDate.of(2016, 6, 30), LocalTime.of(23, 59));
		assertEquals(false, junho.noMeio(hoje.getDiaFim(), hoje.getHoraFim()));		
	}
	

}
