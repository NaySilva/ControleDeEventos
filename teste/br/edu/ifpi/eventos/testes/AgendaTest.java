package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.DataEHora;

public class AgendaTest {

	@Test
	public void Verificar_Se_Hoje_Esta_Entre_Um_Perido() {
		DataEHora dh1 = new DataEHora(LocalDate.of(2016, 7, 1), LocalTime.of(0, 0));
		DataEHora dh2 = new DataEHora(LocalDate.of(2016, 7, 30), LocalTime.of(23, 59));
		Agenda hoje = new Agenda(new DataEHora(LocalDate.now(), LocalTime.now()));
		Agenda julho = new Agenda(dh1, dh2);
		assertEquals(true, julho.noMeio(hoje.getFim()));		
	}
	
	@Test
	public void Verificar_Se_Hoje_Nao_Esta_Entre_Um_Perido() {
		DataEHora dh1 = new DataEHora(LocalDate.of(2016, 6, 1), LocalTime.of(0, 0));
		DataEHora dh2 = new DataEHora(LocalDate.of(2016, 6, 30), LocalTime.of(23, 59));
		Agenda hoje = new Agenda(new DataEHora(LocalDate.now(), LocalTime.now()));
		Agenda junho = new Agenda(dh1, dh2);
		assertEquals(false, junho.noMeio(hoje.getFim()));		
	}
	

}
