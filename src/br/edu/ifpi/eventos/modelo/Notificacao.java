package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.TipoDeNotificacao;

public class Notificacao {
	
	public String descricao;
	public TipoDeNotificacao tipo;
	
	public Notificacao(String descricao, TipoDeNotificacao tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	
	
}
