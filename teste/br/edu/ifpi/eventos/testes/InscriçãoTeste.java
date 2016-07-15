package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.modelo.Simposio;
import br.edu.ifpi.eventos.util.Agenda;

public class InscriçãoTeste {

	Agenda ag1, ag2, ag3;
	Inscrição ins;
	Evento sim;
	Perfil perfil;
	Atividade mc, p, p2;
	
	@Before
	public void Inicialização(){
		ag1 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(8, 0), LocalDate.of(2016, 7, 30), LocalTime.of(12, 0));
		ag2 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(14, 0), LocalDate.of(2016, 7, 30), LocalTime.of(18,00));
		ag3 = new Agenda(LocalDate.of(2016, 7, 30), LocalTime.of(8, 0), LocalDate.of(2016, 7, 30), LocalTime.of(18,00));
		sim = new Simposio("Simposio de Programação", ag3);
		perfil = new Perfil("Participante");
		ins = new Inscrição(sim, perfil);
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
	public void Deve_Marcar_A_Inscrição_Como_Paga_Ao_Receber_Pagamento(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscrição(60);
		assertEquals(true, ins.getPagamento().isPago());
	}
	
	@Test
	public void A_Inscrição_Deve_Esta_Como_Paga_Ao_Receber_Pagamento_Inferior_Do_Total(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscrição(40);
		assertEquals(false, ins.getPagamento().isPago());
	}
	
	@Test
	public void Nao_Deve_Incluir_Atividades_Repetidas(){
		assertEquals(false, ins.adicionarAtividadeDesejada(mc));
	}
	
	@Test
	public void Inscrições_Recem_Criada_Deve_Ter_Zero_Atividades(){
		Inscrição insc2 = new Inscrição(sim, perfil);
		assertEquals(true, insc2.getAtividadesDesejadas().isEmpty());
	}
	
	@Test
	public void Inscrição_Paga_Nao_Deve_Aceitar_Novos_Itens(){
		sim.setPreco(60);
		ins.getPagamento().pagarInscrição(60);
		sim.adicionarAtividade(p);
		assertEquals(false, ins.adicionarAtividadeDesejada(p));
	}

}
