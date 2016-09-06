package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEvento;
import br.edu.ifpi.eventos.util.TipoDeAtividade;
import br.edu.ifpi.eventos.util.TipoDeEvento;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3, ag4, ag5;
	Evento sem, sim;
	Atividade mc;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(14, 00), LocalDate.of(2016, 9, 30), LocalTime.of(18, 00));
		ag2 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(8, 00), LocalDate.of(2016, 9, 29), LocalTime.of(12, 0));
		ag3 = new Agenda(LocalDate.of(2016, 9, 11), LocalTime.of(0, 1), LocalDate.of(2016, 9, 28), LocalTime.of(23, 59));
		ag4 = new Agenda(LocalDate.of(2016, 11, 11), LocalTime.of(0, 1), LocalDate.of(2016, 11, 15), LocalTime.of(23, 59));
		ag5 = new Agenda(LocalDate.of(2016, 9, 28), LocalTime.of(0, 1), LocalDate.of(2016, 9, 28), LocalTime.of(23, 59));
		sem = new Evento("Semana da Quimica", ag1, TipoDeEvento.Semana);
		sim = new Evento("Simposio de programação", ag5, TipoDeEvento.Simposio);
		mc = new Atividade("Nanotecnologia", TipoDeAtividade.Minicurso);
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividades().get(0));
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Com_Inscricoes_Abertas() {
		sem.setPeriodoDeInscricao(new Agenda(LocalDate.of(2016, 9, 04), LocalTime.of(0, 1), LocalDate.of(2016, 9, 30), LocalTime.of(23, 59)));
		assertEquals(StatusDoEvento.InscricoesAbertas, sem.verificarStatus());
	}
	
	@Test
	public void Verificar_Status_De_Um_Evento_Antes_Das_Incricoes_Abrirem(){
		sem.setPeriodoDeInscricao(ag4);
		assertEquals(StatusDoEvento.EmAndamento, sem.verificarStatus());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Nao_Deve_Aceitar_Eventos_Com_Data_Passada(){
		Agenda passada = new Agenda(LocalDate.of(2016, 7, 12), LocalTime.of(8, 0), LocalDate.of(2016, 7, 12), LocalTime.of(18, 0));
		Evento ev = new Evento("Evento", passada, TipoDeEvento.Simposio);
	}
	

	@Test
	public void Evento_Recem_Criado_Deve_Ter_Zero_Atividades(){
		Evento ev = new Evento("Evento", ag1, TipoDeEvento.Simposio);
		assertEquals(true, ev.getAtividades().isEmpty());
	}

	@Test
	public void criarUmEventoCompostoComDoisEventosSatelites(){
		Evento ec = new Evento("EC", TipoDeEvento.Congresso);
		Evento es = new Evento("ev1", TipoDeEvento.Simposio).comEventoPrincipal(ec);
		Evento es2 = new Evento("ev2", TipoDeEvento.Simposio).comEventoPrincipal(ec);
		assertEquals(true, ec.getEventosSatelites().contains(es) & ec.getEventosSatelites().contains(es2));
	}
	
	@Test
	public void mostrarAtvidadesOrdenadas() throws HorarioIndisponivelException{
		EspacoFisico ef = new EspacoFisico("Sala", TipoEspacoFisico.Sala);
		ef.adicionarHorarios(ag1);
		ef.adicionarHorarios(ag2);
		Atividade at1 = new Atividade("at1", TipoDeAtividade.CoffeBreak).emLocal(ef).noHorario(ag1);
		Atividade at2 = new Atividade("at2", TipoDeAtividade.Palestra).emLocal(ef).noHorario(ag2);
		sem.adicionarAtividade(at1);
		sem.adicionarAtividade(at2);
		List<Atividade> atividades = sem.mostrarAtividadesOrdenadasPorAgenda();
		assertEquals(at2, atividades.get(0));
	}


	@Test
	public void mostrarAProximaAtivdade() throws HorarioIndisponivelException{
		List<Agenda> agendas = new ArrayList<>();
		agendas.add(ag2);
		agendas.add(ag1);
		agendas.add(ag3);
		assertEquals(2, sem.posicaoDaProximaAtividade(agendas));
	}

}
