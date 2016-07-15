package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Semana;
import br.edu.ifpi.eventos.modelo.Simposio;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3, ag4, ag5;
	Evento sem, sim;
	Atividade mc;
	
	@Before
	public void inicialização(){
		ag1 = new Agenda(LocalDate.of(2016,7,29), LocalTime.of(8, 00), LocalDate.of(2016, 7, 30), LocalTime.of(12, 00));
		ag2 = new Agenda(LocalDate.of(2016,7,29), LocalTime.of(8, 00), LocalDate.of(2016, 7, 29), LocalTime.of(12, 0));
		ag3 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(0, 1), LocalDate.of(2016, 7, 28), LocalTime.of(23, 59));
		ag4 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(0, 1), LocalDate.of(2016, 7, 13), LocalTime.of(23, 59));
		ag5 = new Agenda(LocalDate.of(2016, 7, 28), LocalTime.of(0, 1), LocalDate.of(2016, 7, 28), LocalTime.of(23, 59));
		sem = new Semana("Semana da Quimica", ag1);
		sim = new Simposio("Simposio de programação", ag5);
		mc = new Minicurso("Nanotecnologia",ag2);
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividades().get(0));
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Com_Inscrições_Abertas() {
		sem.setPeriodoDeInscrição(ag3);
		assertEquals(StatusDoEventoEnum.InscriçõesAbertas, sem.verificarStatus());
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Antes_Das_Incrições_Abrirem(){
		sem.setPeriodoDeInscrição(ag4);
		assertEquals(StatusDoEventoEnum.EmAndamento, sem.verificarStatus());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Não_Deve_Aceitar_Eventos_Com_Data_Passada(){
		Agenda passada = new Agenda(LocalDate.of(2016, 7, 12), LocalTime.of(8, 0), LocalDate.of(2016, 7, 12), LocalTime.of(18, 0));
		Evento ev = new Simposio("Evento", passada);
	}
	

	@Test
	public void Evento_Recem_Criado_Deve_Ter_Zero_Atividades(){
		Evento ev = new Simposio("Evento", ag1);
		assertEquals(true, ev.getAtividades().isEmpty());
	}

}
