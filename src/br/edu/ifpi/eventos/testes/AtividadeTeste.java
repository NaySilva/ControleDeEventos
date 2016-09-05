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
import br.edu.ifpi.eventos.modelo.AtividadePaga;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

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
		Atividade atividade = new Atividade("At1", TipoDeAtividadeEnum.MesaRedonda).emLocal(local).noHorario(agenda);
		assertEquals(agenda, atividade.getAgenda());
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void erroAoPassarUmHorarioNaoDisponivelNoLocal() throws HorarioIndisponivelException{
		Atividade atividade = new Atividade("At1", TipoDeAtividadeEnum.MesaRedonda).emLocal(local).noHorario(agenda2);		
	}
	
	@Test
	public void listarInscritosPorAtividade() throws Exception{
		AtividadePaga atividade = new AtividadePaga("At1", TipoDeAtividadeEnum.MesaRedonda).emLocal(local).noHorario(agenda);		
		Evento ev = new Evento("ev", TipoDeEventoEnum.Congresso);
		ev.adicionarAtividade(atividade);
		Inscricao ins = new Inscricao(ev, new Perfil(Perfil.Participante));
		ins.adicionarProduto(atividade);
		Inscricao ins2 = new Inscricao(ev, new Perfil(Perfil.Participante));
		ins2.adicionarProduto(atividade);
		List<Inscricao> lista = new ArrayList<>();
		lista.add(ins);
		lista.add(ins2);
		assertEquals(true, atividade.getInscricoes().contains(ins) & atividade.getInscricoes().contains(ins2));
	}

}
