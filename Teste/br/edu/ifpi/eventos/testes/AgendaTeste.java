package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import br.edu.ifpi.eventos.util.Agenda;

public class AgendaTeste {

	@Test
	public void Deve_Retornar_Data_E_Hora_Formatadas(){
		Agenda ag = new Agenda("01072016", "1030");
		String resposta = "01/07/2016 - 10:30\n";
		assertEquals(resposta, ag.toString());
	}
	
	@Test(expected=ParseException.class)
	public void Deve_Retornar_Erro_Por_Data_Invalida() throws ParseException{
		Agenda ag = new Agenda("sssss", "1030");
		ag.dataFormatada();
	}
	
	@Test(expected=ParseException.class)
	public void Deve_Retornar_Erro_Por_Hora_Invalida() throws ParseException{
		Agenda ag = new Agenda("01072016", "ss");
		ag.horarioFormatado();
	}
}
