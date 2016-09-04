package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.edu.ifpi.eventos.util.Observer;
import sun.security.provider.certpath.OCSP.RevocationStatus;


@Entity
public class Perfil implements Observer {
	
	public final static String Participante = "Participante";
	public final static String Organizador = "Organizador";
	public final static String Criador = "Criador";
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String descricao;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy="perfil")
	private List<Inscricao> inscricoes;
	private TipoDeUsuario tipo;
	private List<Atividade> suasResponsabilidades;
	
	Perfil() {}

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

	public Long getId() {
		return id;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;	
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}


	@Override
	public String toString() {
		return "Perfil [id=" + id + ", descricao=" + descricao + ", usuario=" + usuario + "]";
	}

	@Override
	public String update(Object arg1) {
		System.out.println((String)arg1);
		return (String)arg1;
	}
	
	
	
	
	
	
	

}
