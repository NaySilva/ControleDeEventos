package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.atividade.TipoDeAtividade;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomGlobal;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPorAtividade;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPromocional;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.pessoa.Pessoa;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class CupomPromocionalTeste {

	@Test
	public void Cupom_Ativo_Se_Estive_No_Periodo_Da_Validade() {
		Agenda val = new Agenda(LocalDateTime.of(2016, 10, 30, 23, 59));
		CupomPromocional l1 = new CupomGlobal("Lote_I", val, new BigDecimal(50));
		l1.verificarAValidade();
		assertEquals(true, l1.getAtivo());		
	}
	
	@Test
	public void Cupom_Nao_Ativo_Se_For_Fora_Da_Validade(){
		Agenda val = new Agenda(LocalDateTime.of(2016, 7, 12, 23, 59));
		CupomPromocional l1 = new CupomGlobal("Lote_I", val, new BigDecimal(50));
		l1.verificarAValidade();
		assertEquals(false, l1.getAtivo());
	}
	
	@Test
	public void Desconto_De_50_Porcento_No_Cupom_Global() throws Exception{
		Agenda val = new Agenda(LocalDateTime.of(2016, 10, 30, 23, 59));
		Evento ev = new EventoBuilder().getEvento();
		Atividade at = new AtividadeBuilder().pagavel().getAtividade();
		ev.adicionarAtividade(at);
		CupomPromocional l1 = new CupomGlobal("Palestra30", val, new BigDecimal(50));
		Item item = new ItemUnico(new BigDecimal(100), at);
		Inscricao inscricao = new Inscricao(ev, new PerfilParticipante(new Usuario(new Pessoa("Maria"))));
		inscricao.adicionarItem(item);
		inscricao.setCupom(l1);
		assertEquals(new BigDecimal(50.0).toBigInteger(), l1.valorDoDesconto(inscricao).toBigInteger());
	}
	
	@Test
	public void Desconto_De_30_Porcento_No_Cupom_Global() throws Exception{
		Agenda val = new Agenda(LocalDateTime.of(2016, 10, 30, 23, 59));
		Evento ev = new EventoBuilder().getEvento();
		Atividade at = new AtividadeBuilder().pagavel().doTipo(TipoDeAtividade.Palestra).getAtividade();
		ev.adicionarAtividade(at);
		CupomPromocional p30 = new CupomPorAtividade("Palestra_30", val, new BigDecimal(30), TipoDeAtividade.Palestra);
		Item item = new ItemUnico(new BigDecimal(100), at);
		Inscricao inscricao = new Inscricao(ev, new PerfilParticipante(new Usuario(new Pessoa("Maria"))));
		inscricao.adicionarItem(item);
		inscricao.setCupom(p30);
		assertEquals(new BigDecimal(30.0).toBigInteger(), p30.valorDoDesconto(inscricao).toBigInteger());
	
	}

}
