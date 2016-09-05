package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3, ag4, ag5;
	Evento sem, sim;
	Atividade mc;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(8, 00), LocalDate.of(2016, 9, 30), LocalTime.of(12, 00));
		ag2 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(8, 00), LocalDate.of(2016, 9, 29), LocalTime.of(12, 0));
		ag3 = new Agenda(LocalDate.of(2016, 9, 11), LocalTime.of(0, 1), LocalDate.of(2016, 9, 28), LocalTime.of(23, 59));
		ag4 = new Agenda(LocalDate.of(2016, 11, 11), LocalTime.of(0, 1), LocalDate.of(2016, 11, 15), LocalTime.of(23, 59));
		ag5 = new Agenda(LocalDate.of(2016, 9, 28), LocalTime.of(0, 1), LocalDate.of(2016, 9, 28), LocalTime.of(23, 59));
		sem = new Evento("Semana da Quimica", ag1, TipoDeEventoEnum.Semana);
		sim = new Evento("Simposio de programação", ag5, TipoDeEventoEnum.Simposio);
		mc = new Atividade("Nanotecnologia", TipoDeAtividadeEnum.Minicurso);
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividades().get(0));
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Com_Inscricoes_Abertas() {
		sem.setPeriodoDeInscricao(new Agenda(LocalDate.of(2016, 9, 04), LocalTime.of(0, 1), LocalDate.of(2016, 9, 30), LocalTime.of(23, 59)));
		assertEquals(StatusDoEventoEnum.InscricoesAbertas, sem.verificarStatus());
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Antes_Das_Incricoes_Abrirem(){
		sem.setPeriodoDeInscricao(ag4);
		assertEquals(StatusDoEventoEnum.EmAndamento, sem.verificarStatus());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Nao_Deve_Aceitar_Eventos_Com_Data_Passada(){
		Agenda passada = new Agenda(LocalDate.of(2016, 7, 12), LocalTime.of(8, 0), LocalDate.of(2016, 7, 12), LocalTime.of(18, 0));
		Evento ev = new Evento("Evento", passada, TipoDeEventoEnum.Simposio);
	}
	

	@Test
	public void Evento_Recem_Criado_Deve_Ter_Zero_Atividades(){
		Evento ev = new Evento("Evento", ag1, TipoDeEventoEnum.Simposio);
		assertEquals(true, ev.getAtividades().isEmpty());
	}

	@Test
	public void criarUmEventoCompostoComDoisEventosSatelites(){
		Evento ec = new Evento("EC", TipoDeEventoEnum.Congresso);
		Evento es = new Evento("ev1", TipoDeEventoEnum.Simposio).comEventoPrincipal(ec);
		Evento es2 = new Evento("ev2", TipoDeEventoEnum.Simposio).comEventoPrincipal(ec);
		assertEquals(true, ec.getEventosSatelites().contains(es) & ec.getEventosSatelites().contains(es2));
	}
	



}
