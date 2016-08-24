package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.cupom.Lote_I;
import br.edu.ifpi.eventos.cupom.Palestras_50;
import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

public class InscricaoTeste {

	Agenda ag1, ag2, ag3, val1, val2;
	Inscricao ins;
	Evento sim;
	Perfil perfil;
	Atividade mc, p, p2;
	CupomPromocional p50, l1, l2;
	
	@Before
	public void Inicializacao() {
		ag1 = new Agenda(LocalDate.of(2016, 8, 30), LocalTime.of(8, 0), LocalDate.of(2016, 8, 30), LocalTime.of(12, 0));
		ag2 = new Agenda(LocalDate.of(2016, 8, 30), LocalTime.of(14, 0), LocalDate.of(2016, 8, 30), LocalTime.of(18,00));
		ag3 = new Agenda(LocalDate.of(2016, 8, 30), LocalTime.of(8, 0), LocalDate.of(2016, 8, 30), LocalTime.of(18,00));
		val1 = new Agenda(LocalDate.of(2016, 8, 30), LocalTime.of(23, 59));
		val2 = new Agenda(LocalDate.of(2016, 8, 12), LocalTime.of(23, 59));
		sim = new Evento("Simposio de Programação", ag3, TipoDeEventoEnum.Simposio);
		perfil = new Perfil(Perfil.Participante);
		ins = new Inscricao(sim, perfil);
		mc = new Atividade("Jogos", ag1, TipoDeAtividadeEnum.Minicurso);
		p = new Atividade("Python", ag2, TipoDeAtividadeEnum.Palestra);
		p2 = new Atividade("Refatorando", ag1, TipoDeAtividadeEnum.Palestra);
		p50 = new Palestras_50(val1);
		l1 = new Lote_I(val1);
		l2 = new Lote_I(val2);
		sim.setPreco(60.00);
		mc.setPreco(50.00);
		p.setPreco(80);
		p2.setPreco(40);
	}

	@Test
	public void Deve_Retornar_A_Primeira_Atividade_Adicionada() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException {
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
		assertEquals(mc, ins.getAtividadesDesejadas().get(0));
	}
	
	@Test
	public void Deve_Verificar_A_Quantidade_De_Atividades() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
		assertEquals(1, ins.getAtividadesDesejadas().size());
	}
	
	@Test
	public void Deve_Permitir_Incricao_Da_Atividade(){
		assertEquals(true, ins.verificarDisponibilidade(p));
	}
	
	
	@Test(expected=AtividadeRepetidaException.class)
	public void Nao_Deve_Incluir_Atividades_Repetidas() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
		ins.adicionarAtividadeDesejada(mc);
	}
	
	@Test
	public void Inscricoes_Recem_Criada_Deve_Ter_Zero_Atividades(){
		Inscricao insc2 = new Inscricao(sim, perfil);
		assertEquals(true, insc2.getAtividadesDesejadas().isEmpty());
	}
	
	@Test(expected=InscricaoPagaException.class)
	public void Inscricao_Paga_Nao_Deve_Aceitar_Novos_Itens() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		ins.getPagamento().pagarInscricao(60);
		sim.adicionarAtividade(p);
		ins.adicionarAtividadeDesejada(p);
	}
	
	@Test(expected=AtividadeInexistenteNoEventoException.class)
	public void Nao_Deve_Incluir_Atividade_Nao_Cadastrada_No_Evento() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		ins.adicionarAtividadeDesejada(p);
	}
	
	@Test(expected=HorarioIndisponivelException.class)
	public void Deve_Ter_Horario_Disponivel_Para_Adicionar_Tal_Atividade() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
		sim.adicionarAtividade(p2);
		ins.adicionarAtividadeDesejada(p2);
	}
	
	@Test
	public void Adicionar_Cupom_Palestras_50_E_Verificar_Desconto() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(p);
		ins.adicionarAtividadeDesejada(p);
		ins.adicionarCupom(p50);
		assertEquals(40.0,p50.valorDoDesconto(ins), 0.00001);
	}
	
	@Test
	public void Verificar_Total_Desconto_Depois_De_Adicionar_Cupons() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(p);
		ins.adicionarAtividadeDesejada(p);
		ins.adicionarCupom(p50);
		ins.adicionarCupom(l1);
		assertEquals(70, ins.totalDeDesconto(), 0.00001);
	}
	
	@Test
	public void Verificar_Valor_Total_Com_Desconto_De_Uma_Inscricao_Completa_E_Com_Cupons() throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
		sim.adicionarAtividade(p);
		ins.adicionarAtividadeDesejada(p);
		ins.adicionarCupom(p50);
		ins.adicionarCupom(l1);
		assertEquals(120, ins.calcularTotalComDesconto(), 0.00001);
	}

	@Test
	public void Nao_Deve_Aceitar_Desconto_De_Cupons_Nao_Ativos(){
		ins.adicionarCupom(l2);
		assertEquals(false, ins.getCupons().contains(l2));
	}

}
