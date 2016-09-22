package br.edu.ifpi.eventos.modelo.perfil;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import br.edu.ifpi.eventos.modelo.usuario.Usuario;
import br.edu.ifpi.eventos.util.Observer;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "perfil_class_mae")
public abstract class Perfil implements Observer{
	

	@Id
	@GeneratedValue
	protected Long id;
	@ManyToOne
	protected Usuario usuario;
	
	Perfil() {}
	
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
