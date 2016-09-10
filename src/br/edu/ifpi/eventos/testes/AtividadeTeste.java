package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.Agenda;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Item;
import br.edu.ifpi.eventos.modelo.ItemUnico;
import br.edu.ifpi.eventos.modelo.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.TipoDeAtividade;
import br.edu.ifpi.eventos.util.TipoDeEvento;
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
		Inscricao ins = new Inscricao(ev, new PerfilParticipante(new Usuario()));
		Item item = new ItemUnico("Palesta A", 30, atividade);
		ins.adicionarItem(item);
		Inscricao ins2 = new Inscricao(ev, new PerfilParticipante(new Usuario()));
		ins2.adicionarItem(item);
		List<Inscricao> lista = new ArrayList<>();
		lista.add(ins);
		lista.add(ins2);
		assertEquals(true, item.getInscricoes().contains(ins) & item.getInscricoes().contains(ins2));
	}

}
