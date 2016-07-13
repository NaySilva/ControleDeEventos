package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Agenda;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.util.DataEHora;

public class AtividadeTeste {
	
	DataEHora dh1, dh2, dh3, dh4;
	Agenda ag1, ag2;
	Atividade mc, p, p2;
	
	@Before
	public void inicialização(){
		dh1 = new DataEHora(LocalDate.of(2016, 7, 7),LocalTime.of(8, 00));
		dh2 = new DataEHora(LocalDate.of(2016, 7, 7),LocalTime.of(12, 00));
		dh3 = new DataEHora(LocalDate.of(2016, 7, 7), LocalTime.of(14, 00));
		dh4 = new DataEHora(LocalDate.of(2016, 7, 7), LocalTime.of(18,00));
		ag1 = new Agenda(dh1,dh2);
		ag2 = new Agenda(dh3, dh4);
		mc = new Minicurso("Banco de dados",ag1);
		p = new Palestra("Python", ag2);
		p2 = new Palestra("Java",ag1);
		
	}

	@Test
	public void Deve_Retornar_True_Por_Agendas_Iguais_Na_Comparacao() {
		assertEquals(true,mc.compararHorario(p2));
	}
	
	@Test
	public void Deve_Retornar_False_Por_Palestra_Ser_Depois_Do_Minicurso_Na_Comparacao(){
		assertEquals(false,mc.compararHorario(p));
	}
	
	@Test
	public void Deve_Retornar_False_Por_Minicurso_Ser_Antes_Da_Palestra_Na_Comparacao(){
		assertEquals(false, p.compararHorario(mc)); 
	}
	
	@Test
	public void Deve_Verificar_Vagas_Disponiveis(){
		mc.setCapacidade(10);
		mc.adicionarInscricao(new Inscrição());
		mc.adicionarInscricao(new Inscrição());
		assertEquals(8, mc.verificarVagas());
	}
	
	@Test
	public void Deve_verificar_A_Duracao_Da_Atividade(){
		assertEquals("0 - 240", mc.getAgenda().duração());
		
	}


}
