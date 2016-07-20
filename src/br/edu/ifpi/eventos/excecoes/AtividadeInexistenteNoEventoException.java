package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class AtividadeInexistenteNoEventoException extends Exception {

	public AtividadeInexistenteNoEventoException() {
		super("Essa atividade não está cadastrada no evento dessa inscrição");
	}
	
	

}
