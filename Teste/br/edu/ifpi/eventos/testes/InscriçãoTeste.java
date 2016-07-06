package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.util.Agenda;

public class InscriçãoTeste {
	
	Inscrição ins;
	Minicurso mc;
	Palestra p;
	Agenda ag1 = new Agenda("07072016", "1300");
	Agenda ag2 = new Agenda("07072016", "0800");
	
	@Before
	public void Inicialização(){
		ins = new Inscrição();
		mc = new Minicurso("Jogos", ag1);
		p = new Palestra("Python", ag2);
		ins.adicionarAtividadeDesejada(mc);
		ins.adicionarAtividadeDesejada(p);
	}

	@Test
	public void Deve_Retornar_A_Primeira_Atividade_Adicionada() {
		assertEquals(mc, ins.getAtividadesDesejadas().get(0));
	}
	
	@Test
	public void Deve_Verificar_A_Quantidade_De_Atividades(){
		assertEquals(2, ins.getAtividadesDesejadas().size());
	}
	
	@Test
	public void Nao_Deve_Permitir_Inscricao_Da_Atividade(){
		Palestra p2 = new Palestra("Refatorando", ag1);
		assertEquals(false, ins.verificarDisponibilidade(p2));
	}
	
	

}
