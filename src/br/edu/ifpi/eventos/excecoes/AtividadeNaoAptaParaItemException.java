package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class AtividadeNaoAptaParaItemException extends Exception {

	public AtividadeNaoAptaParaItemException() {
		super("Essa atividade não pode ser um item porque não é pagavel");
	}
	
}
