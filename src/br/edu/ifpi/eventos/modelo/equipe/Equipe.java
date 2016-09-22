package br.edu.ifpi.eventos.modelo.equipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.perfil.PerfilOrganizador;
@Entity
public class Equipe {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@OneToOne
	private PerfilOrganizador dono;
	@ManyToOne
	private Evento evento;
	@ManyToMany(mappedBy="equipes")
	private List<PerfilOrganizador> organizadores;
	
	protected Equipe(){}
	
	public Equipe(String descricao, PerfilOrganizador dono, Evento evento) {
		this.descricao = descricao;
		this.dono = dono;
		this.evento = evento;
		this.organizadores = new ArrayList<PerfilOrganizador>();
	}
	
	public void adicionarPerfil(PerfilOrganizador perfil){
		this.organizadores.add(perfil);
		perfil.adicionarEquipe(this);
	}
	
	public PerfilOrganizador getDono() {
		return dono;
	}
	
	public List<PerfilOrganizador> getOrganizadores() {
		return Collections.unmodifiableList(organizadores);
	}

	public String getDescricao() {
		return descricao;
	}
}
