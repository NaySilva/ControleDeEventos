package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class AtividadeTeste {

	@Test
	public void mudarHorarioPassandoPelasRestricoes() throws HorarioIndisponivelException {
		Agenda agenda= new Agenda(LocalDate.of(2016, 8, 8), LocalTime.of(8,0), LocalDate.of(2016, 8, 8), LocalTime.of(12, 0));
		EspacoFisico local = new EspacoFisico("sala", TipoEspacoFisico.Sala);
		local.adicionarHorarios(agenda);
		Atividade atividade = new Atividade("At1", TipoDeAtividadeEnum.MesaRedonda).emLocal(local).noHorario(agenda);
		assertEquals(agenda, atividade.getAgenda());
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void erroAoPassarUmHorarioNaoDisponivelNoLocal() throws HorarioIndisponivelException{
		Agenda agenda= new Agenda(LocalDate.of(2016, 8, 8), LocalTime.of(8,0), LocalDate.of(2016, 8, 8), LocalTime.of(12, 0));
		Agenda agenda2 = new Agenda(LocalDate.of(2016, 8, 8), LocalTime.of(14,0), LocalDate.of(2016, 8, 8), LocalTime.of(18, 0));
		EspacoFisico local = new EspacoFisico("sala", TipoEspacoFisico.Sala);
		local.adicionarHorarios(agenda);
		Atividade atividade = new Atividade("At1", TipoDeAtividadeEnum.MesaRedonda).emLocal(local).noHorario(agenda2);		
	}

}
