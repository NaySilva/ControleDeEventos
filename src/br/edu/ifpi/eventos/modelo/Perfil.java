package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	
	private String descri��o;
	private List<Inscri��o> inscri��es;

	public Perfil(String descri��o) {
		this.descri��o = descri��o;
		this.inscri��es = new ArrayList<Inscri��o>();
	}

	public String getDescri��o() {
		return descri��o;
	}

	public List<Inscri��o> getInscri��es() {
		return inscri��es;
	}
	
	
	
	

}
