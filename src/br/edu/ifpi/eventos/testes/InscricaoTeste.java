package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.enums.TipoDeAtividade;
import br.edu.ifpi.eventos.enums.TipoEspacoFisico;
import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeNaoAptaParaItemException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.CupomInativoException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPromocional;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomGlobal;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPorAtividade;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisicoBuilder;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.pessoa.Pessoa;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class InscricaoTeste {

	Agenda ag1, ag2, val1, val2;
	Inscricao ins;
	Evento sim;
	PerfilParticipante perfil;
	Atividade mc, p, p2;
	CupomPromocional p50, l1, l2;
	Item item1, item2, item3;
	
	@Before
	public void Inicializacao() throws HorarioIndisponivelException, AtividadeNaoAptaParaItemException {
		ag1 = new Agenda(LocalDateTime.of(2016, 9, 30, 8, 0), LocalDateTime.of(2016, 9, 30, 12, 0));
		ag2 = new Agenda(LocalDateTime.of(2016, 9, 30, 14, 0), LocalDateTime.of(2016, 9, 30, 18, 0));
		val1 = new Agenda(LocalDateTime.of(2016, 9, 30, 23, 59));
		val2 = new Agenda(LocalDateTime.of(2016, 8, 24, 23, 59));
		sim = new EventoBuilder().getEvento();
		perfil = new PerfilParticipante(new Usuario(new Pessoa("Maria")));
		ins = new Inscricao(sim, perfil);
		EspacoFisico local = new EspacoFisicoBuilder().comDescricao("sala A").doTipo(TipoEspacoFisico.Sala).getEspacoFisico();
		local.adicionarHorarios(ag1);
		local.adicionarHorarios(ag2);
		mc = new AtividadeBuilder().comNome("Jogos").doTipo(TipoDeAtividade.Minicurso).emLocal(local).noHorario(ag1).pagavel().getAtividade();
		p = new AtividadeBuilder().comNome("Python").doTipo(TipoDeAtividade.Palestra).emLocal(local).noHorario(ag2).pagavel().getAtividade();
		p2 = new AtividadeBuilder().comNome("Refatorando").doTipo(TipoDeAtividade.Palestra).emLocal(local).noHorario(ag2).pagavel().getAtividade();
		p50 = new CupomPorAtividade("Palestra50", val1, new BigDecimal(50), TipoDeAtividade.Palestra);
		l1 = new CupomGlobal("Lote_I", val1, new BigDecimal(50));
		l2 = new CupomGlobal("Lote_II", val2, new BigDecimal(50));
		item1 = new ItemUnico(new BigDecimal(50), mc);
		item2 = new ItemUnico(new BigDecimal(80), p);
		item3 = new ItemUnico(new BigDecimal(40), p2);
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
		ins.getPagamento().pagarInscricao(new BigDecimal(50));
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
	}
	
	@Test(expected=AtividadeInexistenteNoEventoException.class)
	public void Nao_Deve_Incluir_Atividade_Nao_Cadastrada_No_Evento() throws Exception{
		ins.adicionarItem(item2);
	}
	
	@Test
	public void Valor_Total_Bruto_Da_Inscricao() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
		assertEquals(new BigDecimal(130.0).toPlainString(), ins.calcularTotalBruto().toPlainString());
	}
	
	@Test
	public void Verificar_Valor_Total_Com_Desconto_De_Uma_Inscricao_Completa_E_Com_Cupons() throws Exception{
		sim.adicionarAtividade(mc);
		ins.adicionarItem(item1);
		sim.adicionarAtividade(p);
		ins.adicionarItem(item2);
		ins.setCupom(l1);
		assertEquals(65, ins.calcularTotalComDesconto().doubleValue(), 0.00001);
	}

	@Test(expected=CupomInativoException.class)
	public void Nao_Deve_Aceitar_Desconto_De_Cupons_Nao_Ativos() throws Exception{
		ins.setCupom(l2);
	}
	
		

}
