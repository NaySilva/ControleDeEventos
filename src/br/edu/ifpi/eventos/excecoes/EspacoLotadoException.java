package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class EspacoLotadoException extends Exception{
	
	public EspacoLotadoException() {
		super("Este espa�o j� est� lotado");
	}
	
}
