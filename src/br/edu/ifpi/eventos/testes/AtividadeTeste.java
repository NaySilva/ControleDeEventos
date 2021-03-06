package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.enums.TipoDeAtividade;
import br.edu.ifpi.eventos.enums.TipoDeEvento;
import br.edu.ifpi.eventos.enums.TipoEspacoFisico;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisicoBuilder;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.pessoa.Pessoa;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class AtividadeTeste {
	
	Agenda agenda, agenda2;
	EspacoFisico local;
	Atividade atividade;
	
	@Before
	public void inicializacao(){
		agenda = new Agenda(LocalDateTime.of(2016, 8, 8, 8, 0), LocalDateTime.of(2016, 8, 8, 12, 0));
		agenda2 = new Agenda(LocalDateTime.of(2016, 8, 8, 14, 0), LocalDateTime.of(2016, 8, 8, 18, 0));
		local = new EspacoFisicoBuilder().comDescricao("sala").doTipo(TipoEspacoFisico.Sala).getEspacoFisico();
		local.adicionarHorarios(agenda);
	}

	@Test
	public void mudarHorarioPassandoPelasRestricoes() throws HorarioIndisponivelException {
		atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda).getAtividade();
		assertEquals(agenda, atividade.getAgenda());
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void erro_Ao_Passar_Um_Horario_Nao_Disponivel_No_Local() throws HorarioIndisponivelException{
		atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda2).getAtividade();		
	}
	
	@Test
	public void listarInscritosPorAtividade() throws Exception{
		atividade = new AtividadeBuilder().comNome("At1").doTipo(TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda).pagavel().getAtividade();	
		Evento ev = new EventoBuilder().comNome("ev").doTipo(TipoDeEvento.Congresso).getEvento();
		ev.adicionarAtividade(atividade);
		Inscricao ins = new Inscricao(ev, new PerfilParticipante(new Usuario(new Pessoa("Maria"))));
		Item item = new ItemUnico(new BigDecimal("30"), atividade);
		ins.adicionarItem(item);
		Inscricao ins2 = new Inscricao(ev, new PerfilParticipante(new Usuario(new Pessoa("Jo�o"))));
		ins2.adicionarItem(item);
		List<Inscricao> lista = new ArrayList<>();
		lista.add(ins);
		lista.add(ins2);
		assertEquals(true, item.getInscricoes().contains(ins) & item.getInscricoes().contains(ins2));
	}
	
	@Test
	public void permitir_Incluir_Responsaveis_Para_Uma_Atividade(){
		Atividade at = new AtividadeBuilder().comNome("Palestra").doTipo(TipoDeAtividade.Palestra).getAtividade();
		Responsabilidade res = new Responsabilidade(at, new Pessoa("Maria"));
		assertEquals(true, at.getResponsaveis().contains(res));
	}
	
	
}
