package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.util.Map;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public class CadastroEvento {
	
	private Evento evento;
	private StatusDoEventoEnum status;
	private Agenda começoInscrição;
	private Agenda fimInscrição;
	
	
	public void verificarStatus(){
		LocalDate hoje = LocalDate.now();
		
	}
	
}
