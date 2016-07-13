package br.edu.ifpi.eventos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Agenda {
	
	
	private LocalDate data;
	private	LocalTime hora;
	
	public Agenda(LocalDate data, LocalTime hora) {
		this.data = data;
		this.hora = hora;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHora() {
		return hora;
	}
	
	@Override
	public String toString() {
		return data.toString() + " " + hora.toString();
	}

	public int periodoEmDias(Agenda fim) {
		Period periodo = Period.between(this.data, fim.data);		
		return periodo.getDays();
	}

	public long duraçãoEmMinutos(Agenda fim) {
		Duration duracao = Duration.between(this.hora, fim.hora);
		return duracao.toMinutes();
		
	}
	

}
