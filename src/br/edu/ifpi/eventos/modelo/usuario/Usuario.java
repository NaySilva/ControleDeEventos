package br.edu.ifpi.eventos.modelo.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Pessoa pessoa;
	private String login;
	private String senha;
	@Transient
	private List<PerfilParticipante> perfis;
	
	Usuario(){}
	
	public Usuario(Pessoa pessoa){
		
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + pessoa.getNome() + ", login=" + login + ", senha=" + senha + "]";
	}
	
	
	
	
	

}
