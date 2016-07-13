package br.edu.ifpi.eventos.modelo;

import java.util.List;

public class Instituicao {
	
	private String nome;
	private List<Evento> eventos;
	
	public Instituicao(String nome) {
		this.nome = nome;
	}
	
	public void adicionarEvento(Evento ev){
		this.eventos.add(ev);
	}

}
