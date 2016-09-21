package br.edu.ifpi.eventos.modelo.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String login;
	private String senha;
	@OneToMany(mappedBy="usuario")
	private List<PerfilParticipante> perfis;
	
	public Usuario(){}
	
	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + "]";
	}
	
	
	
	
	

}
