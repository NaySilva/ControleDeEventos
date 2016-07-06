package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.util.Agenda;

public class AtividadeTeste {

	@Test
	public void Deve_Retornar_True_Por_Agendas_Iguais_Na_Comparacao() {
		Agenda ag = new Agenda("07072016","1300");
		Atividade mc = new Minicurso("Banco de dados",ag);
		Atividade p = new Palestra("Python",ag);
		assertEquals(true,mc.compararHorario(p));
	}
	
	@Test
	public void Deve_Retornar_False_Por_Agendas_Diferentes_Na_Comparacao(){
		Agenda ag = new Agenda("07072016","1300");
		Agenda ag2 = new Agenda("07072016","0800");
		Atividade mc = new Minicurso("Banco de dados",ag);
		Atividade p = new Palestra("Python",ag2);
		assertEquals(false,mc.compararHorario(p));
	}
	
	@Test
	public void Deve_Verificar_Vagas_Disponiveis(){
		Agenda ag = new Agenda("07072016","1300");
		Atividade mc = new Minicurso("Banco de dados", ag);
		mc.setCapacidade(10);
		mc.adicionarInscricao(new Inscrição());
		mc.adicionarInscricao(new Inscrição());
		assertEquals(8, mc.verificarVagas());
	}


}
