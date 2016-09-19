package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.StatusDoEvento;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3;
	Evento sem, sim;
	Atividade mc;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(14, 00), LocalDate.of(2016, 9, 30), LocalTime.of(18, 00));
		ag2 = new Agenda(LocalDate.of(2016,9,29), LocalTime.of(8, 00), LocalDate.of(2016, 9, 29), LocalTime.of(12, 0));
		ag3 = new Agenda(LocalDate.of(2016, 9, 11), LocalTime.of(0, 1), LocalDate.of(2016, 9, 28), LocalTime.of(23, 59));
		sem = new Evento("Semana da Quimica", TipoDeEvento.Semana).comInscricoesPara(ag1);
		sim = new Evento("Simposio de programa��o", TipoDeEvento.Simposio).comInscricoesPara(ag3);
		mc = new Atividade("Nanotecnologia", TipoDeAtividade.Minicurso);
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividadesC().get(0));
	}
	
	@Test
	public void Mostrar_Status_Inscricoes_Abertas_Se_For_No_Periodo_Da_Inscricao(){
		sim.verificarPeriodoDeInscricao();
		assertEquals(StatusDoEvento.InscricoesAbertas, sim.getStatus());
	}
	
	@Test
	public void Mostrar_Status_Em_Andamento_Se_N�o_For_No_Periodo_Da_Inscricao(){
		sem.verificarPeriodoDeInscricao();
		assertEquals(StatusDoEvento.EmAndamento, sem.getStatus());
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
