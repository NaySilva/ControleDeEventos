package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;


import br.edu.ifpi.eventos.util.Agenda;

public abstract class Atividade {
	
	private String nome;
	private Agenda come�o;
	private Agenda fim;
	private double preco;
	private List<Inscri��o> inscri��es;
	private int capacidade;
	
	public Atividade(String nome, Agenda come�o, Agenda fim) {
		this.nome = nome;
		this.come�o = come�o;
		this.fim = fim;
		this.preco = 0.0;
		this.inscri��es = new ArrayList<>();
	}
	
	public String dura��o(){
		int dias = come�o.periodoEmDias(fim);
		long minutos = come�o.dura��oEmMinutos(fim);
		return dias + " - " + minutos;
	}
	
	public void adicionarInscricao(Inscri��o inscri��o){
		this.inscri��es.add(inscri��o);
	}
	
	public boolean compararHorario(Atividade at){//true se est� no mesmo horario
		boolean antes = come�o.getHora().compareTo(at.getFim().getHora())==1;
		boolean depois = fim.getHora().compareTo(at.getCome�o().getHora())==-1;
		return !antes && !depois;
	}
	
	private Agenda getFim() {
		return fim;
	}
	public int verificarVagas(){
		return capacidade - inscri��es.size();
	}
	
	public String getNome() {
		return nome;
	}

	public Agenda getCome�o() {
		return come�o;
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
	
	
	



}
