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
import br.edu.ifpi.eventos.modelo.EspacoFisico;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Item;
import br.edu.ifpi.eventos.modelo.ItemUnico;
import br.edu.ifpi.eventos.modelo.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividade;
import br.edu.ifpi.eventos.util.TipoDeEvento;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class InscricaoTeste {

	Agenda ag1, ag2, ag3, val1, val2;
	Inscricao ins;
	Evento sim;
	PerfilParticipante perfil;
	Atividade mc, p, p2;
	CupomPromocional p50, l1, l2;
	Item item1, item2, item3;
	
	@Before
	public void Inicializacao() throws HorarioIndisponivelException {
		ag1 = new Agenda(LocalDate.of(2016, 9, 30), LocalTime.of(8, 0), LocalDate.of(2016, 9, 30), LocalTime.of(12, 0));
		ag2 = new Agenda(LocalDate.of(2016, 9, 30), LocalTime.of(14, 0), LocalDate.of(2016, 9, 30), LocalTime.of(18,00));
		ag3 = new Agenda(LocalDate.of(2016, 9, 30), LocalTime.of(8, 0), LocalDate.of(2016, 9, 30), LocalTime.of(18,00));
		val1 = new Agenda(LocalDate.of(2016, 9, 30), LocalTime.of(23, 59));
		val2 = new Agenda(LocalDate.of(2016, 8, 24), LocalTime.of(23, 59));
		sim = new Evento("Simposio de Programação", TipoDeEvento.Simposio);
		perfil = new PerfilParticipante(new Usuario());
		ins = new Inscricao(sim, perfil);
		EspacoFisico local = new EspacoFisico("sala A", TipoEspacoFisico.Sala);
		local.adicionarHorarios(ag1);
		local.adicionarHorarios(ag2);
		mc = new Atividade("Jogos", TipoDeAtividade.Minicurso).emLocal(local).noHorario(ag1);
		p = new Atividade("Python", TipoDeAtividade.Palestra).emLocal(local).noHorario(ag2);
		p2 = new Atividade("Refatorando",TipoDeAtividade.Palestra).emLocal(local).noHorario(ag2);
		p50 = new Palestras_50(val1);
		l1 = new Lote_I(val1);
		l2 = new Lote_I(val2);
		item1 = new ItemUnico("minicurso", 50, mc);
		item2 = new ItemUnico("palestra1", 80, p);
		item3 = new ItemUnico("palestra2", 40, p2);
	}

	@Test
	public void Deve_Retornar_O_Primeiro_Item_Adicionada() throws Exception {
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		assertEquals(item1, ins.getCarrinho().get(0));
	}
	
	@Test
	public void Deve_Verificar_A_Quantidade_De_Atividades() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		assertEquals(1, ins.getCarrinho().size());
	}
	
	
	@Test(expected=AtividadeRepetidaException.class)
	public void Nao_Deve_Incluir_Atividades_Repetidas() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		ins.adicionarItem(item1);
	}
	
	@Test
	public void Inscricoes_Recem_Criada_Deve_Ter_Zero_Atividades(){
		Inscricao insc2 = new Inscricao(sim, perfil);
		assertEquals(true, insc2.getCarrinho().isEmpty());
	}
	
	@Test(expected=InscricaoPagaException.class)
	public void Inscricao_Paga_Nao_Deve_Aceitar_Novos_Itens() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		ins.getPagamento().pagarInscricao(50);
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
	}
	
	@Test(expected=AtividadeInexistenteNoEventoException.class)
	public void Nao_Deve_Incluir_Atividade_Nao_Cadastrada_No_Evento() throws Exception{
		ins.adicionarItem(item2);
	}
	
	@Test
	public void Adicionar_Cupom_Palestras_50_E_Verificar_Desconto() throws Exception{
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
		ins.adicionarCupom(p50);
		assertEquals(40.0,p50.valorDoDesconto(ins), 0.00001);
	}
	
	@Test
	public void Verificar_Total_Desconto_Depois_De_Adicionar_Cupons() throws Exception{
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
		ins.adicionarCupom(p50);
		ins.adicionarCupom(l1);
		assertEquals(80, ins.totalDeDesconto(), 0.00001);
	}
	
	@Test
	public void Verificar_Valor_Total_Com_Desconto_De_Uma_Inscricao_Completa_E_Com_Cupons() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
		ins.adicionarCupom(p50);
		ins.adicionarCupom(l1);
		assertEquals(25, ins.calcularTotalComDesconto(), 0.00001);
	}

	@Test
	public void Nao_Deve_Aceitar_Desconto_De_Cupons_Nao_Ativos(){
		ins.adicionarCupom(l2);
		assertEquals(false, ins.getCupons().contains(l2));
	}
	
		

}
