package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class AtividadeTeste {
	
	Agenda agenda, agenda2;
	EspacoFisico local;
	
	@Before
	public void inicializacao(){
		agenda = new Agenda(LocalDate.of(2016, 8, 8), LocalTime.of(8,0), LocalDate.of(2016, 8, 8), LocalTime.of(12, 0));
		agenda2 = new Agenda(LocalDate.of(2016, 8, 8), LocalTime.of(14,0), LocalDate.of(2016, 8, 8), LocalTime.of(18, 0));
		local = new EspacoFisico("sala", TipoEspacoFisico.Sala);
		local.adicionarHorarios(agenda);
	}

	@Test
	public void mudarHorarioPassandoPelasRestricoes() throws HorarioIndisponivelException {
		Atividade atividade = new Atividade("At1", TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda);
		assertEquals(agenda, atividade.getAgenda());
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void erroAoPassarUmHorarioNaoDisponivelNoLocal() throws HorarioIndisponivelException{
		Atividade atividade = new Atividade("At1", TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda2);		
	}
	
	@Test
	public void listarInscritosPorAtividade() throws Exception{
		Atividade atividade = new Atividade("At1", TipoDeAtividade.MesaRedonda).emLocal(local).noHorario(agenda);		
		Evento ev = new Evento("ev", TipoDeEvento.Congresso);
		ev.adicionarAtividade(atividade);
		atividade.setPagavel(true);
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
		Atividade at = new Atividade("Palestra", TipoDeAtividade.Palestra);
		Responsabilidade res = new Responsabilidade(at, new Usuario());
		assertEquals(true, at.getResponsaveis().contains(res));
	}
	
	@Test
	public void Marcar_Atividade_Como_Realizada(){
		Atividade at = new Atividade("Palestra", TipoDeAtividade.Palestra);
		at.setRealizado(true);
		assertEquals(true, at.isRealizado());
	}
	
}
