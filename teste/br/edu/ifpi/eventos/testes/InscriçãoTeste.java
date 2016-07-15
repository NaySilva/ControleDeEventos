package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscri��o;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.modelo.Simposio;
import br.edu.ifpi.eventos.util.Agenda;

public class Inscri��oTeste {

	Agenda ag1, ag2, ag3;
	Inscri��o ins;
	Evento sim;
	Perfil perfil;
	Atividade mc, p, p2;
	
	@Before
	public void Inicializa��o(){
		ag1 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(8, 0), LocalDate.of(2016, 7, 30), LocalTime.of(12, 0));
		ag2 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(14, 0), LocalDate.of(2016, 7, 30), LocalTime.of(18,00));
		ag3 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(8, 0), LocalDate.of(2016, 7, 30), LocalTime.of(18,00));
		sim = new Simposio("Simposio de Programa��o", ag3);
		perfil = new Perfil("Participante");
		ins = new Inscri��o(sim, perfil);
		mc = new Minicurso("Jogos", ag1);
		p = new Palestra("Python", ag2);
		p2 = new Palestra("Refatorando", ag1);
		sim.adicionarAtividade(mc);
		ins.adicionarAtividadeDesejada(mc);
	}

	@Test
	public void Deve_Retornar_A_Primeira_Atividade_Adicionada() {
		assertEquals(mc, ins.getAtividadesDesejadas().get(0));
	}
	
	@Test
	public void Deve_Verificar_A_Quantidade_De_Atividades(){
		assertEquals(1, ins.getAtividadesDesejadas().size());
	}
	
	@Test
	public void Nao_Deve_Permitir_Inscricao_Da_Atividade(){
		assertEquals(false, ins.verificarDisponibilidade(p2));
	}
	
	@Test
	public void Deve_Permitir_Incricao_Da_Atividade(){
		assertEquals(true, ins.verificarDisponibilidade(p));
	}
	
	@Test
	public void Deve_Marcar_A_Inscri��o_Como_Paga_Ao_Receber_Pagamento(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscri��o(60);
		assertEquals(true, ins.getPagamento().isPago());
	}
	
	@Test
	public void A_Inscri��o_Deve_Esta_Como_Paga_Ao_Receber_Pagamento_Inferior_Do_Total(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscri��o(40);
		assertEquals(false, ins.getPagamento().isPago());
	}
	
	@Test
	public void Nao_Deve_Incluir_Atividades_Repetidas(){
		assertEquals(false, ins.adicionarAtividadeDesejada(mc));
	}
	
	@Test
	public void Inscri��es_Recem_Criada_Deve_Ter_Zero_Atividades(){
		Inscri��o insc2 = new Inscri��o(sim, perfil);
		assertEquals(true, insc2.getAtividadesDesejadas().isEmpty());
	}
	
	@Test
	public void Inscri��o_Paga_Nao_Deve_Aceitar_Novos_Itens(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscri��o(60);
		sim.adicionarAtividade(p);
		assertEquals(false, ins.adicionarAtividadeDesejada(p));
	}

}
