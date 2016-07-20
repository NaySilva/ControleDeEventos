package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;


public class Atividade {
	
	private String nome;
	private Agenda agenda;
	private double preco;
	private TipoDeAtividadeEnum tipo;
	private List<Inscricao> inscricoes;
	private int capacidade;
	
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
	
	public int verificarVagas(){
		return capacidade - inscricoes.size();
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
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
