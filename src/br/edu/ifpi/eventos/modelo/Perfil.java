package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	
	private String descrição;
	private List<Inscrição> inscrições;

	public Perfil(String descrição) {
		this.descrição = descrição;
		this.inscrições = new ArrayList<Inscrição>();
	}

	public String getDescrição() {
		return descrição;
	}

	public List<Inscrição> getInscrições() {
		return inscrições;
	}
	
	
	
	

}
