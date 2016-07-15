package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.modelo.Simposio;
import br.edu.ifpi.eventos.util.Agenda;

public class AtividadeTeste {
	
	@Test
	public void Deve_Verificar_Vagas_Disponiveis(){
		Agenda ag1 = new Agenda(LocalDate.of(2016, 7, 16),LocalTime.of(8, 00), LocalDate.of(2016, 7, 16), LocalTime.of(18,00));
		Evento sim = new Simposio("Simposio de Programaçao", ag1);
		sim.setCapacidade(10);
		Inscrição insc = new Inscrição(sim, new Perfil("Participante"));
		assertEquals(9, sim.verificarVagas());
	}
	


}
