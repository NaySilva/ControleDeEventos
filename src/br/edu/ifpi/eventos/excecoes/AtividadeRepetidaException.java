package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class AtividadeRepetidaException extends Exception {

	public AtividadeRepetidaException() {
		super("Essa atividade ja foi adicionada!");
	}
	
	
	

}
