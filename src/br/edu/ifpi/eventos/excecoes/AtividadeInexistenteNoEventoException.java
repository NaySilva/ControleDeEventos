package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class AtividadeInexistenteNoEventoException extends Exception {

	public AtividadeInexistenteNoEventoException() {
		super("Essa atividade n�o est� cadastrada no evento dessa inscri��o");
	}
	
	

}
