package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividade;
import br.edu.ifpi.eventos.util.TipoDeEvento;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class NotificacaoTeste {

	@Test
	public void mostrarNotificacaoAoAdicionarAtividadeNoEventoDepoisDeJaTaInscrito() throws HorarioIndisponivelException {
		Agenda ag1 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(8,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		Agenda ag2 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(10,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		EspacoFisico local = new EspacoFisico("sala A", TipoEspacoFisico.Sala);
		local.adicionarHorarios(ag1);
		local.adicionarHorarios(ag2);
		Atividade atividade = new Atividade("Palestra",TipoDeAtividade.Palestra).emLocal(local).noHorario(ag1);
		Atividade atividade2 = new Atividade("minicurso", TipoDeAtividade.Minicurso).emLocal(local).noHorario(ag2);
		Evento evento = new Evento("Ev", TipoDeEvento.Simposio);
		PerfilParticipante perfil = new PerfilParticipante(new Usuario());
		Inscricao inscricao = new Inscricao(evento, perfil);
		evento.adicionarAtividade(atividade);
		String mensagem = "Nova notificação do evento Ev:\nNova atividade adicionada: Palestra - Palestra";
		assertEquals(mensagem, evento.getNotificacao());
	}
	
	@Test
	public void naoMostrarNotificacaoAoAdicionarAtividadeNoEventoDepoisDeJaTaInscrito() throws HorarioIndisponivelException {
		Agenda ag1 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(8,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		Agenda ag2 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(10,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		EspacoFisico local = new EspacoFisico("sala A", TipoEspacoFisico.Sala);
		local.adicionarHorarios(ag1);
		local.adicionarHorarios(ag2);
		Atividade atividade = new Atividade("Palestra",TipoDeAtividade.Palestra).emLocal(local).noHorario(ag1);
		Atividade atividade2 = new Atividade("minicurso", TipoDeAtividade.Minicurso).emLocal(local).noHorario(ag2);
		Evento evento = new Evento("Ev", TipoDeEvento.Simposio);
		PerfilParticipante perfil = new PerfilParticipante(new Usuario());
		evento.adicionarAtividade(atividade);
		Inscricao inscricao = new Inscricao(evento, perfil);
		assertEquals("", evento.getNotificacao());
	}

}
