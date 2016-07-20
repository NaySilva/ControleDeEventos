package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.lang.model.element.PackageElement;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.cupom.Lote_I;
import br.edu.ifpi.eventos.cupom.Palestras_50;
import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Pagamento;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

public class PagamentoTeste {
	
	Agenda ag1;
	Evento sem;
	Perfil perfil;
	Inscricao insc;
	Pagamento pagamento;
	
	@Before
	public void inicializacao(){
		ag1 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(8, 00), LocalDate.of(2016, 7, 29), LocalTime.of(18, 00));
		sem = new Evento("Semana de Quimica", ag1, TipoDeEventoEnum.Semana);
		perfil = new Perfil("Participante");
		insc = new Inscricao(sem, perfil);
		pagamento = new Pagamento(insc);
		sem.setPreco(60.00);
	}
	
	@Test
	public void Deve_Marcar_A_Inscricao_Como_Paga_Ao_Receber_Pagamento(){
		pagamento.pagarInscricao(60);
		assertEquals(true, pagamento.isPago());
	}
	@Test
	public void O_Pagamento_Deve_Esta_Como_Nao_Pago_Ao_Receber_Pagamento_Inferior_Do_Total(){
		pagamento.pagarInscricao(40);
		assertEquals(false, pagamento.isPago());
	}
	
	@Test
	public void Dia_Do_Pagamento_É_Hoje(){
		pagamento.pagarInscricao(60);
		LocalDate dia = pagamento.getHorarioPagamento().getDiaFim();
		assertEquals(LocalDate.now(), dia);
	}
	
	
	
	

}
