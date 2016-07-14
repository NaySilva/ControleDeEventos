package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.Simposio;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.DataEHora;

public class AtividadeTeste {
	
	DataEHora dh1, dh2, dh3, dh4;
	Agenda ag1, ag2, ag3;
	Atividade mc, p, p2;
	Evento sim;
	Usuario usu1, usu2;
	Inscrição insc1, insc2;
	
	@Before
	public void inicialização(){
		dh1 = new DataEHora(LocalDate.of(2016, 7, 16),LocalTime.of(8, 00));
		dh2 = new DataEHora(LocalDate.of(2016, 7, 16),LocalTime.of(12, 00));
		dh3 = new DataEHora(LocalDate.of(2016, 7, 16), LocalTime.of(14, 00));
		dh4 = new DataEHora(LocalDate.of(2016, 7, 16), LocalTime.of(18,00));
		ag1 = new Agenda(dh1,dh2);
		ag2 = new Agenda(dh3, dh4);
		ag3 = new Agenda(dh1, dh4);
		sim = new Simposio("Simposio", ag3);
		mc = new Minicurso("Banco de dados",ag1);
		p = new Palestra("Python", ag2);
		p2 = new Palestra("Java",ag1);
		
	}
	
	@Test
	public void Deve_Verificar_Vagas_Disponiveis(){
		sim.setCapacidade(10);
		insc1 = new Inscrição(sim, usu1);
		insc2 = new Inscrição(sim, usu2);
		assertEquals(8, sim.verificarVagas());
	}
	
	@Test
	public void Deve_verificar_A_Duracao_Da_Atividade(){
		assertEquals("0 - 240", mc.getAgenda().duração());
		
	}


}
