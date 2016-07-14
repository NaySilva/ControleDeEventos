package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.TipoDeNotificação;

public class Notificacao {
	
	public String descricao;
	public TipoDeNotificação tipo;
	
	public Notificacao(String descricao, TipoDeNotificação tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	
	
}
