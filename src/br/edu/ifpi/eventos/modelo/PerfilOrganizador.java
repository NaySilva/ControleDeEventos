package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import br.edu.ifpi.eventos.util.Perfil;

public class PerfilOrganizador extends Perfil {
	
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
