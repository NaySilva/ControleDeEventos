package br.edu.ifpi.eventos.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String login;
	@NotNull @Size(min=3, message="O tamanho da  senha tem que ser no minimo 3")
	private String senha;
	@OneToMany(mappedBy="usuario")
	private List<Perfil> perfis;
	
	public Usuario(){}
	
	public Usuario(String nome) {
		this.nome = nome;
	}

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

	@Override
	public String toString() {
		return "Usuario [id= " + id + "nome=" + nome + ", login=" + login + ", senha=" + senha + "]";
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	

}
