package br.edu.ifpi.eventos.excecoes;

@SuppressWarnings("serial")
public class CupomInativoException extends Exception {

	public CupomInativoException() {
		super("Este Cupom Não Está Ativo");
	}
	
}
