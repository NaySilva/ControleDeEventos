package br.edu.ifpi.eventos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Agenda {
	
	
	private String data;
	private String hora;
	
	public Agenda(String data, String hora) {
		this.data = data;
		this.hora = hora;
	}
	
	public String dataFormatada() throws ParseException{
		SimpleDateFormat formato1 = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		return formato2.format(formato1.parse(data));
	}
	
	public String horarioFormatado() throws ParseException {
		SimpleDateFormat formato1 = new SimpleDateFormat("hhmm");
		SimpleDateFormat formato2 = new SimpleDateFormat("hh:mm");
		return formato2.format(formato1.parse(hora));
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}
	
	@Override
	public String toString() {
		String agenda;
		try {
			agenda = dataFormatada() + " - " + horarioFormatado() + "\n";
		} catch (ParseException e) {
			agenda = "Erro na Agenda";
		}
		return agenda;
	}
	

}
