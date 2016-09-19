package br.edu.ifpi.eventos.modelo.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
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
	@Transient
	private List<PerfilParticipante> perfis;
	@Transient
	private List<Responsabilidade> resposabilidades;
	
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
