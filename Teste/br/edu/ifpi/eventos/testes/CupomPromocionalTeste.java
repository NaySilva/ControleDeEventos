package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import br.edu.ifpi.eventos.cupom.Lote_I;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.DataEHora;
import br.edu.ifpi.eventos.util.TipoDeCupom;

public class CupomPromocionalTeste {

	@Test
	public void Deve_Ser_Ativo_Se_Estive_No_Periodo_Da_Validade() {
		DataEHora val = new DataEHora(LocalDate.of(2016, 7, 16), LocalTime.of(23, 59));
		Agenda ag1 = new Agenda(val);
		CupomPromocional l1 = new Lote_I(ag1, TipoDeCupom.Geral);
		l1.verificarAValidade();
		assertEquals(true, l1.getAtivo());		
	}
	
	@Test
	public void Nao_Deve_Ser_Ativo_Se_For_Fora_Da_Validade(){
		DataEHora val = new DataEHora(LocalDate.of(2016, 7, 12), LocalTime.of(23, 59));
		Agenda ag1 = new Agenda(val);
		CupomPromocional l1 = new Lote_I(ag1, TipoDeCupom.Geral);
		l1.verificarAValidade();
		assertEquals(false, l1.getAtivo());
	}

}
