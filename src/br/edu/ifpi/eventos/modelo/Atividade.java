package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;


public abstract class Atividade {
	
	private String nome;
	private Agenda agenda;
	private double preco;
	private List<Inscri��o> inscri��es;
	private int capacidade;
	
	public Atividade(String nome, Agenda agenda) {
		this.nome = nome;
		this.agenda = agenda;
		this.preco = 0.0;
		this.inscri��es = new ArrayList<>();
	}
	
	public void adicionarInscricao(Inscri��o inscri��o){
		this.inscri��es.add(inscri��o);
	}
	
	public boolean compararHorario(Atividade at){
		return agenda.compararHorario(at.getAgenda());
	}
	
	public int verificarVagas(){
		return capacidade - inscri��es.size();
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

	
	
	
	



}
