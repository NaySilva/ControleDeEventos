package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.pagamento.Pagamento;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class PagamentoTeste {
	
	Agenda ag1;
	Evento sem;
	PerfilParticipante perfil;
	Inscricao insc;
	Pagamento pagamento;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDateTime.of(2016, 9, 11, 8, 0), LocalDateTime.of(2016, 9, 29, 18, 0));
		sem = new EventoBuilder().comNome("Semana de Quimica").doTipo(TipoDeEvento.Semana).getEvento();
		perfil = new PerfilParticipante(new Usuario());
		insc = new Inscricao(sem, perfil);
		pagamento = new Pagamento(insc);
	}
	
	@Test
	public void Deve_Marcar_A_Inscricao_Como_Paga_Ao_Receber_Pagamento(){
		pagamento.pagarInscricao(new BigDecimal(0));
		assertEquals(true, pagamento.isPago());
	}
	@Test
	public void O_Pagamento_Deve_Esta_Como_Nao_Pago_Ao_Receber_Pagamento_Diferente_Do_Total(){
		pagamento.pagarInscricao(new BigDecimal(40));
		assertEquals(false, pagamento.isPago());
	}
	
	@Test
	public void Dia_Do_Pagamento_É_Hoje(){
		pagamento.pagarInscricao(new BigDecimal(0));
		LocalDateTime dia = pagamento.getHorarioPagamento().getFim();
		assertEquals(LocalDateTime.now().toLocalDate(), dia.toLocalDate());
	}
	
}
