package br.edu.ifpi.eventos.modelo.pessoa;

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
	
	protected Pessoa(){}
	
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
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Pessoa com nome: "+ nome;
	}

}
