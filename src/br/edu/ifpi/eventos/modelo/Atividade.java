package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
@Entity
public class Atividade {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToOne
	private Agenda agenda;
	private double preco;
	private TipoDeAtividadeEnum tipo;
	@ManyToMany
	private List<Inscricao> inscricoes;
	private EspacoFisico local;
	private List<Responsavel> responsaveis;
	private boolean realizado;
	
	public Atividade(String nome, Agenda agenda, TipoDeAtividadeEnum tipo) {
		this.nome = nome;
		this.agenda = agenda;
		this.tipo = tipo;
		this.preco = 0.0;
		this.inscricoes = new ArrayList<>();
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
	}
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Agenda getAgenda() {
		return agenda;
	}
	
	public String getNome() {
		return nome;
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public TipoDeAtividadeEnum getTipo() {
		return tipo;
	}

	
	
	
	
	



}
