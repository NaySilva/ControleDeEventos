package br.edu.ifpi.eventos.util;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpi.eventos.modelo.Usuario;

public abstract class Perfil implements Observer{
	

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	protected Usuario usuario;
	
	public Perfil(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String update(Object mensagem) {
		System.out.println((String)mensagem);
		return (String)mensagem;
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
}
