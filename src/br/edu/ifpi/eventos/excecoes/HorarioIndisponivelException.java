package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class HorarioIndisponivelException extends Exception {

	public HorarioIndisponivelException() {
		super("Você não tem horário disponivel para essa atividade!");
	}
	
	

}
