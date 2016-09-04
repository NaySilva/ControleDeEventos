package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

public class NotificacaoTeste {

	
	public void mostrarTresNotificacoes() {
		Agenda ag1 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(8,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		Agenda ag2 = new Agenda(LocalDate.of(2016, 9, 1), LocalTime.of(10,0), LocalDate.of(2016,9,1), LocalTime.of(11, 0));
		Atividade atividade = new Atividade("Palestra", ag1, TipoDeAtividadeEnum.Palestra);
		Atividade atividade2 = new Atividade("minicurso", ag2, TipoDeAtividadeEnum.Minicurso);
		Evento evento = new Evento("Ev", TipoDeEventoEnum.Simposio);
		Perfil perfil = new Perfil(Perfil.Participante);
		evento.adicionarAtividade(atividade);
		evento.adicionarAtividade(atividade2);
		Inscricao inscricao = new Inscricao(evento, perfil);
		inscricao.adicionarProduto(atividade);
		inscricao.adicionarProduto(atividade2);
		atividade.setPreco(30);
	}
	
	
	public static void main(String[] args) {
		NotificacaoTeste  not = new NotificacaoTeste();
		not.mostrarTresNotificacoes();
	}

}
