package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perfil {
	
	private String descricao;
	private List<Inscricao> inscricoes;

	public Perfil(String descricao) {
		this.descricao = descricao;
		this.inscricoes = new ArrayList<Inscricao>();
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}
	
	
	
	

}
