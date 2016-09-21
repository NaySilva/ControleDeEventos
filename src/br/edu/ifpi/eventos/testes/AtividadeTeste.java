package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisicoBuilder;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
import br.edu.ifpi.eventos.modelo.usuario.Pessoa;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class AtividadeTeste {
	
	Agenda agenda, agenda2;
	EspacoFisico local;
	
	@Before
	public void inicializacao(){
		agenda = new Agenda(LocalDateTime.of(2016, 8, 8, 8, 0), LocalDateTime.of(2016, 8, 8, 12, 0));
		agenda2 = new Agenda(LocalDateTime.of(2016, 8, 8, 14, 0), LocalDateTime.of(2016, 8, 8, 18, 0));
		local = new EspacoFisicoBuilder().comDescricao("sala").doTipo(TipoEspacoFisico.Sala).getEspacoFisico();
		local.adicionarHorarios(agenda);
	}

	@Test
	public void mudarHorarioPassandoPelasRestricoes() throws HorarioIndisponivelException {
		Atividade atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda).getAtividade();
		assertEquals(agenda, atividade.getAgenda());
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void erroAoPassarUmHorarioNaoDisponivelNoLocal() throws HorarioIndisponivelException{
		Atividade atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda2).getAtividade();		
	}
	
	@Test
	public void listarInscritosPorAtividade() throws Exception{
		Atividade atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda).pagavel().getAtividade();	
		Evento ev = new EventoBuilder().comNome("ev").doTipo(TipoDeEvento.Congresso).getEvento();
		ev.adicionarAtividade(atividade);
		Inscricao ins = new Inscricao(ev, new PerfilParticipante(new Usuario()));
		Item item = new ItemUnico(new BigDecimal("30"), atividade);
		ins.adicionarItem(item);
		Inscricao ins2 = new Inscricao(ev, new PerfilParticipante(new Usuario()));
		ins2.adicionarItem(item);
		List<Inscricao> lista = new ArrayList<>();
		lista.add(ins);
		lista.add(ins2);
		assertEquals(true, item.getInscricoes().contains(ins) & item.getInscricoes().contains(ins2));
	}
	
	@Test
	public void permitir_Incluir_Responsaveis_Para_Uma_Atividade(){
		Atividade at = new AtividadeBuilder().comNome("Palestra").doTipo(TipoDeAtividade.Palestra).getAtividade();
		Responsabilidade res = new Responsabilidade(at, new Pessoa());
		assertEquals(true, at.getResponsaveis().contains(res));
	}
	
	
}
