package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.util.Observer;
import br.edu.ifpi.eventos.util.Perfil;

public class PerfilOrganizador extends Perfil {
	
	private List<Equipe> equipes;
	@OneToMany(mappedBy="perfil")
	private List<Evento> eventos;
	

	public PerfilOrganizador(Usuario usuario) {
		super(usuario);
		this.eventos = new ArrayList<Evento>();
		this.equipes = new ArrayList<Equipe>();
	}
	
	public void adicionarEquipe(Equipe equipe){
		this.equipes.add(equipe);
	}
	
	public void adicionarEvento(Evento evento){
		this.eventos.add(evento);
	}	
	
	public List<Equipe> getEquipes() {
		return Collections.unmodifiableList(equipes);
	}
	
	public List<Evento> getEventos() {
		return Collections.unmodifiableList(eventos);
	}

}
