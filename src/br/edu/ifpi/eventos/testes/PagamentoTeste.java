package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.lang.model.element.PackageElement;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPromocional;
import br.edu.ifpi.eventos.modelo.cupompromocional.Lote_I;
import br.edu.ifpi.eventos.modelo.cupompromocional.Palestras_50;
import br.edu.ifpi.eventos.modelo.evento.Evento;
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
		ag1 = new Agenda(LocalDate.of(2016, 9, 11), LocalTime.of(8, 00), LocalDate.of(2016, 9, 29), LocalTime.of(18, 00));
		sem = new Evento("Semana de Quimica",TipoDeEvento.Semana);
		perfil = new PerfilParticipante(new Usuario());
		insc = new Inscricao(sem, perfil);
		pagamento = new Pagamento(insc);
	}
	
	@Test
	public void Deve_Marcar_A_Inscricao_Como_Paga_Ao_Receber_Pagamento(){
		pagamento.pagarInscricao(0);
		assertEquals(true, pagamento.isPago());
	}
	@Test
	public void O_Pagamento_Deve_Esta_Como_Nao_Pago_Ao_Receber_Pagamento_Diferente_Do_Total(){
		pagamento.pagarInscricao(40);
		assertEquals(false, pagamento.isPago());
	}
	
	@Test
	public void Dia_Do_Pagamento_�_Hoje(){
		pagamento.pagarInscricao(0);
		LocalDate dia = pagamento.getHorarioPagamento().getDiaFim();
		assertEquals(LocalDate.now(), dia);
	}
	
	
	
	

}
