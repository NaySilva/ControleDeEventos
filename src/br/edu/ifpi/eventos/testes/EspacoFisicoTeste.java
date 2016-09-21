package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisicoBuilder;
import br.edu.ifpi.eventos.modelo.espacofisico.TipoEspacoFisico;

public class EspacoFisicoTeste {

	@Test
	public void local_Fisico_Com_Locais_Internos_Dentro_DeLocais_Internos() {
		EspacoFisico ef = new EspacoFisicoBuilder().comDescricao("Campus X").doTipo(TipoEspacoFisico.Campus).getEspacoFisico();
		EspacoFisico ef1 =  new EspacoFisicoBuilder().comDescricao("Predio A").doTipo(TipoEspacoFisico.Predio).comLocalExterno(ef).getEspacoFisico();
		EspacoFisico ef11 = new EspacoFisicoBuilder().comDescricao("Auditorio C").doTipo(TipoEspacoFisico.Auditorio).comLocalExterno(ef1).getEspacoFisico();
		EspacoFisico ef12 = new EspacoFisicoBuilder().comDescricao("Sala 3").doTipo(TipoEspacoFisico.Sala).comLocalExterno(ef1).getEspacoFisico();
		assertEquals(true, ef.getLocaisInternos().contains(ef1) & (ef1.getLocaisInternos().size()==2));
	}
	
	@Test
	public void horarioDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisicoBuilder().comDescricao("Sala 1").doTipo(TipoEspacoFisico.Sala).getEspacoFisico();
		Agenda agDoLocal = new Agenda(LocalDateTime.of(2016, 9, 4, 0, 0), LocalDateTime.of(2016, 9, 4, 23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDateTime.of(2016, 9, 4, 8, 0), LocalDateTime.of(2016, 9, 4, 12, 0));
		assertEquals(true, ef.disponivelNoHorario(ag));
	}
	
	@Test 
	public void HorarioNaoDisponivelParaOLocal(){
		EspacoFisico ef = new EspacoFisicoBuilder().comDescricao("Sala 1").doTipo(TipoEspacoFisico.Sala).getEspacoFisico();
		Agenda agDoLocal = new Agenda(LocalDateTime.of(2016, 9, 4, 0, 0), LocalDateTime.of(2016, 9, 4, 23, 59));
		ef.adicionarHorarios(agDoLocal);
		Agenda ag = new Agenda(LocalDateTime.of(2016, 9, 5, 8, 0), LocalDateTime.of(2016, 9, 5, 12, 0));
		assertEquals(false, ef.disponivelNoHorario(ag));
	}

}
