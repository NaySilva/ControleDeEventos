package br.edu.ifpi.eventos.modelo.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	@OneToMany(mappedBy="responsavel")
	private List<Responsabilidade> resposabilidades;
	
	Pessoa(){}
	
	public Pessoa(String nome) {
		this.nome = nome;
		this.resposabilidades = new ArrayList<Responsabilidade>();
	}
	
	public void adicionarResposabilidade(Responsabilidade responsabilidade){
		this.resposabilidades.add(responsabilidade);
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getEmail() {
		return email;
	}

}
