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
import br.edu.ifpi.eventos.util.Perfil;
import br.edu.ifpi.eventos.util.TipoDeParticipacao;
import sun.security.provider.certpath.OCSP.RevocationStatus;


@Entity
public final class PerfilParticipante implements Observer, Perfil {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy="perfil")
	private List<Inscricao> inscricoes;
	private TipoDeParticipacao tipo;
	
	PerfilParticipante() {}

	public PerfilParticipante(Usuario usuario) {
		this.usuario = usuario;
		this.inscricoes = new ArrayList<Inscricao>();
	}
	
	public void adicionarInscricao(Inscricao inscricao, TipoDeParticipacao tipo){
		this.inscricoes.add(inscricao);
		this.tipo = tipo;
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public Long getId() {
		return id;
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
		return "Perfil [id=" + id + ", usuario=" + usuario + "]";
	}

	@Override
	public String update(Object arg1) {
		System.out.println((String)arg1);
		return (String)arg1;
	}
	
	
	
	
	
	
	

}
