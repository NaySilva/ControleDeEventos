package br.edu.ifpi.eventos.modelo;

import java.util.List;

public class Usuario {
	
	private String nome;
	private Perfil perfil;
	private String login;
	private String senha;
	private List<Perfil> perfis;
	
	public Usuario(String nome, Perfil perfil) {
		this.nome = nome;
		this.perfil = perfil;
	}
	
	

}
