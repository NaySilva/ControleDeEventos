package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.rmi.server.UID;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.spi.TimeZoneNameProvider;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.PerfilDeUsuario;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.Agenda;

public class InscriçãoTeste {

	Agenda ag1, ag2, ag3, ag4;
	Inscrição ins;
	Evento sim;
	Usuario usu;
	Atividade mc, p, p2;
	
	@Before
	public void Inicialização(){
		ag1 = new Agenda(LocalDate.of(2016, 7, 7), LocalTime.of(8, 0));
		ag2 = new Agenda(LocalDate.of(2016, 7, 7), LocalTime.of(12, 0));
		ag3 = new Agenda(LocalDate.of(2016, 7, 7), LocalTime.of(14, 0));
		ag4 = new Agenda(LocalDate.of(2016, 7, 7), LocalTime.of(18,00));
		sim = new Simposio("Simposio de Programação",ag1,ag4);
		usu = new Usuario("Maria", new PerfilDeUsuario("Participante"));
		ins = new Inscrição(sim, usu);
		mc = new Minicurso("Jogos", ag1, ag2);
		p = new Palestra("Python", ag3, ag4);
		p2 = new Palestra("Refatorando", ag1, ag2);
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
	
	

}
