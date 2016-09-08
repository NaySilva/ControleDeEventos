package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Equipe {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@ManyToOne
	private PerfilOrganizador dono;
	@ManyToOne
	private Evento evento;
	@OneToMany(mappedBy="equipe")
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
