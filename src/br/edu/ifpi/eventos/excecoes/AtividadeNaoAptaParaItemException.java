package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class AtividadeNaoAptaParaItemException extends Exception {

	public AtividadeNaoAptaParaItemException() {
		super("Essa atividade n�o pode ser um item porque n�o � pagavel");
	}
	
}
