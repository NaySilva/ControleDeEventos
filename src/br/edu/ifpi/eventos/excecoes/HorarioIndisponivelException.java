package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class HorarioIndisponivelException extends Exception {

	public HorarioIndisponivelException() {
		super("Voc� n�o tem hor�rio disponivel para essa atividade!");
	}
	
	

}
