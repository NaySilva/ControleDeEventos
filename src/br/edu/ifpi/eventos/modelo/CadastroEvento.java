package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.util.Map;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public class CadastroEvento {
	
	private Evento evento;
	private StatusDoEventoEnum status;
	private Agenda come�oInscri��o;
	private Agenda fimInscri��o;
	
	
	public void verificarStatus(){
		LocalDate hoje = LocalDate.now();
		
	}
	
}
