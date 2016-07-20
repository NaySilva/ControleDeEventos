package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class InscricaoPagaException extends Exception {

	public InscricaoPagaException() {
		super("Sua inscrição já está paga!");
	}
	
	

}
