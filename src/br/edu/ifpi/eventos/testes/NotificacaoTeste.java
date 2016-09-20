package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class NotificacaoTeste {

	@Test
	public void mostrarNotificacaoAoAdicionarAtividadeNoEventoDepoisDeJaTaInscrito() throws HorarioIndisponivelException {
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 1, 8, 0), LocalDateTime.of(2016, 9, 1, 11, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 1, 10, 0), LocalDateTime.of(2016, 9, 1, 11, 0));
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
		Agenda ag1 = new Agenda(LocalDateTime.of(2016, 9, 1, 8, 0), LocalDateTime.of(2016, 9, 1, 11, 0));
		Agenda ag2 = new Agenda(LocalDateTime.of(2016, 9, 1, 10, 0), LocalDateTime.of(2016, 9, 1, 11, 0));
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
