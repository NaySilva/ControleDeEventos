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
public class Perfil {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@ManyToOne(optional=false)
	private Usuario usuario;
	@OneToMany(mappedBy="perfil")
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
