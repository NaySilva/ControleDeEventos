package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.cupom.Lote_I;
import br.edu.ifpi.eventos.cupom.Palestras_50;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Pagamento;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.modelo.Semana;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeCupom;

public class CalcularContaTest {
	
	Agenda ag1, ag2, ag3, ag4, val1, val2;
	Evento sem;
	Perfil perfil;
	Inscrição insc;
	Atividade mc, p, p2;
	CupomPromocional p50, l1, l2;
	Pagamento cc;
	
	@Before
	public void inicialização(){
		ag1 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(8, 00), LocalDate.of(2016, 7, 15), LocalTime.of(18, 00));
		ag2 = new Agenda(LocalDate.of(2016, 7, 11), LocalTime.of(8, 00), LocalDate.of(2016, 7, 11), LocalTime.of(12, 00));
		ag3 = new Agenda(LocalDate.of(2016, 7, 13), LocalTime.of(14, 00), LocalDate.of(2016, 7, 13), LocalTime.of(16, 00));
		ag4 = new Agenda(LocalDate.of(2016, 7, 13), LocalTime.of(14, 00), LocalDate.of(2016, 7, 13), LocalTime.of(18, 00));
		val1 = new Agenda(LocalDate.of(2016, 7, 16), LocalTime.of(23, 59));
		val2 = new Agenda(LocalDate.of(2016, 7, 12), LocalTime.of(23, 59));
		sem = new Semana("Semana de Quimica", ag1);
		perfil = new Perfil("Participante");
		insc = new Inscrição(sem, perfil);
		mc = new Minicurso("Analitica", ag2);
		p = new Palestra("GMRV", ag3);
		p2 = new Palestra("Nanotecnologia", ag4);
		cc = new Pagamento(insc);
		p50 = new Palestras_50(val1, TipoDeCupom.Especifico);
		l1 = new Lote_I(val1, TipoDeCupom.Geral);
		l2 = new Lote_I(val2, TipoDeCupom.Geral);
		sem.setPreco(60.00);
		mc.setPreco(50.00);
		p.setPreco(80);
		p2.setPreco(40);
	}

	@Test
	public void Verificar_O_Total_Bruto_Sem_Entra_P2_E_Mc_Pelas_Retricoes() {
		sem.adicionarAtividade(p);
		sem.adicionarAtividade(p2);
		insc.adicionarAtividadeDesejada(mc);
		insc.adicionarAtividadeDesejada(p);
		insc.adicionarAtividadeDesejada(p2);
		assertEquals(140, cc.calcularTotalBruto(), 0.00001);
	}
	
	@Test
	public void Adicionar_Cupom_Palestras_50_E_Verificar_Desconto(){
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(p);
		cc.adicionarCupom(p50);
		assertEquals(40.0,p50.valorDoDesconto(cc.getInscrição()), 0.00001);
	}
	
	@Test
	public void Verificar_Total_Desconto_Depois_De_Adicionar_Cupons(){
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(p);
		cc.adicionarCupom(p50);
		cc.adicionarCupom(l1);
		assertEquals(70, cc.totalDeDesconto(), 0.00001);
	}
	
	@Test
	public void Verificar_Valor_Total_Com_Desconto_De_Uma_Inscricao_Completa_E_Com_Cupons(){
		sem.adicionarAtividade(mc);
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(mc);
		insc.adicionarAtividadeDesejada(p);
		cc.adicionarCupom(p50);
		cc.adicionarCupom(l1);
		assertEquals(120, cc.calcularTotalComDesconto(), 0.00001);
	}
	
	@Test
	public void Nao_Deve_Aceitar_Desconto_De_Cupons_Nao_Ativos(){
		cc.adicionarCupom(l2);
		assertEquals(false, cc.getCupons().contains(l2));
	}

}
