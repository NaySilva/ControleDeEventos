package br.edu.ifpi.eventos.modelo;

public class Usuario {
	
	private String nome;
	private PerfilDeUsuario perfil;
	private String login;
	private String senha;
	
	public Usuario(String nome, PerfilDeUsuario perfil) {
		this.nome = nome;
		this.perfil = perfil;
	}

}
