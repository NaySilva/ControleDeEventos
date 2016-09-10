package br.edu.ifpi.eventos.modelo.perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.modelo.equipe.Equipe;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;
@Entity
@DiscriminatorValue("perfil-organizador")
public class PerfilOrganizador extends Perfil {
	
	@ManyToMany
	private List<Equipe> equipes;
	

	public PerfilOrganizador(Usuario usuario) {
		super(usuario);

		this.equipes = new ArrayList<Equipe>();
	}
	
	public void adicionarEquipe(Equipe equipe){
		this.equipes.add(equipe);
	}
	
	public List<Equipe> getEquipes() {
		return Collections.unmodifiableList(equipes);
	}

}
