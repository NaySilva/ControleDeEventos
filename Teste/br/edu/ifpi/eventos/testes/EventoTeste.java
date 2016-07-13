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
import br.edu.ifpi.eventos.util.Agenda;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3;
	Evento sem;
	Atividade mc;
	
	@Before
	public void inicialização(){
		ag1 = new Agenda(LocalDate.of(2016,7,11), LocalTime.of(8, 00));
		ag2 = new Agenda(LocalDate.of(2016, 7, 15), LocalTime.of(12, 00)); 
		ag3 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(12, 0));
		sem = new Semana("Semana da Quimica", ag1, ag2);
		mc = new Minicurso("Games",ag1, ag3);
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividades().get(0));
	}

}
