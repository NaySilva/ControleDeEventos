package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.TipoDeNotifica��o;

public class Notificacao {
	
	public String descricao;
	public TipoDeNotifica��o tipo;
	
	public Notificacao(String descricao, TipoDeNotifica��o tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	
	
}
