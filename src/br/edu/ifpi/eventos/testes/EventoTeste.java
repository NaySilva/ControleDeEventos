package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.evento.StatusDoEvento;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;

public class EventoTeste {
	
	Agenda ag1, ag2, ag3;
	Evento sem, sim;
	Atividade mc;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDateTime.of(2016, 9, 29, 14, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		ag2 = new Agenda(LocalDateTime.of(2016, 9, 29, 8, 0), LocalDateTime.of(2016, 9, 29, 12, 0));
		ag3 = new Agenda(LocalDateTime.of(2016, 9, 11, 0, 1), LocalDateTime.of(2016, 9, 28, 23, 59));
		sem = new EventoBuilder().comNome("Semana da Quimica").doTipo(TipoDeEvento.Semana).comInscricoesPara(ag1).getEvento();
		sim = new EventoBuilder().comNome("Simposio de programação").doTipo(TipoDeEvento.Simposio).comInscricoesPara(ag3).getEvento();
		mc = new AtividadeBuilder().comNome("Nanotecnologia").doTipo(TipoDeAtividade.Minicurso).getAtividade();
	}

	@Test
	public void Adicionar_Atividade_Posiveis_No_Evento() {
		sem.adicionarAtividade(mc);
		assertEquals(mc, sem.getAtividades().get(0));
	}
	
	@Test
	public void Mostrar_Status_Inscricoes_Abertas_Se_For_No_Periodo_Da_Inscricao(){
		sim.verificarPeriodoDeInscricao();
		assertEquals(StatusDoEvento.InscricoesAbertas, sim.getStatus());
	}
	
	@Test
	public void Mostrar_Status_Em_Andamento_Se_Não_For_No_Periodo_Da_Inscricao(){
		sem.verificarPeriodoDeInscricao();
		assertEquals(StatusDoEvento.EmAndamento, sem.getStatus());
	}
	
	@Test
	public void criarUmEventoCompostoComDoisEventosSatelites(){
		Evento ec = new Evento();
		Evento es = new EventoBuilder().comEventoPrincipal(ec).getEvento();
		Evento es2 = new EventoBuilder().comEventoPrincipal(ec).getEvento();
		assertEquals(true, ec.getEventosSatelites().contains(es) & ec.getEventosSatelites().contains(es2));
	}
	
	@Test
	public void mostrarAtvidadesOrdenadas() throws HorarioIndisponivelException{
		EspacoFisico ef = new EspacoFisico("Sala", TipoEspacoFisico.Sala);
		ef.adicionarHorarios(ag1);
		ef.adicionarHorarios(ag2);
		Atividade at1 = new AtividadeBuilder().comNome("at1").doTipo(TipoDeAtividade.CoffeBreak).emLocal(ef).noHorario(ag1).getAtividade();
		Atividade at2 = new AtividadeBuilder().comNome("at2").doTipo(TipoDeAtividade.Palestra).emLocal(ef).noHorario(ag2).getAtividade();
		sem.adicionarAtividade(at1);
		sem.adicionarAtividade(at2);
		List<Atividade> atividades = sem.atividadesOrdenadas();
		assertEquals(at2, atividades.get(0));
	}


}
