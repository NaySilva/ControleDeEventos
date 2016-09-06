package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equipe {
	
	private String descricao;
	private PerfilOrganizador dono;
	private Evento evento;
	private List<PerfilOrganizador> organizadores;
	
	public Equipe(String descricao, PerfilOrganizador dono, Evento evento) {
		this.descricao = descricao;
		this.dono = dono;
		this.evento = evento;
		this.organizadores = new ArrayList<PerfilOrganizador>();
	}
	

	public void adicionarPerfil(PerfilOrganizador perfil){
		this.organizadores.add(perfil);
		perfil.adicionarEvento(evento);
		perfil.adicionarEquipe(this);
	}
	
	public PerfilOrganizador getDono() {
		return dono;
	}
	
	public List<PerfilOrganizador> getOrganizadores() {
		return Collections.unmodifiableList(organizadores);
	}

}
