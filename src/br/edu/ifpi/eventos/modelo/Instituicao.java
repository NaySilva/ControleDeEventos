package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
	
	private String nome;
	private List<Evento> eventos;
	
	public Instituicao(String nome) {
		this.nome = nome;
		this.eventos = new ArrayList<Evento>();
	}

	public String getNome() {
		return nome;
	}
	
	

}
