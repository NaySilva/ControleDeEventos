package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.util.DataEHora;

public class DataEHoraTeste {
	
	DataEHora ag1, ag2;
	
	@Before
	public void inicialização(){
		ag1 = new DataEHora(LocalDate.of(2016, 7, 1), LocalTime.of(8, 00));
		ag2 = new DataEHora(LocalDate.of(2016, 7, 3), LocalTime.of(10, 30));
	}

	@Test
	public void Deve_Retornar_Data_E_Hora_Formatadas(){
		String resposta = "2016-07-01 08:00";
		assertEquals(resposta, ag1.toString());
	}
	
	@Test
	public void Deve_Mostra_Duração_Entre_Duas_Datas_Em_Dias(){
		assertEquals(2, ag1.periodoEmDias(ag2));
	}
	
	@Test
	public void Deve_Mostra_Duracao_Ente_Duas_Horas_Em_Minutos(){
		assertEquals(150, ag1.duraçãoEmMinutos(ag2));
	}
}
