package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;


import br.edu.ifpi.eventos.util.Agenda;

public abstract class Atividade {
	
	private String nome;
	private Agenda começo;
	private Agenda fim;
	private double preco;
	private List<Inscrição> inscrições;
	private int capacidade;
	
	public Atividade(String nome, Agenda começo, Agenda fim) {
		this.nome = nome;
		this.começo = começo;
		this.fim = fim;
		this.preco = 0.0;
		this.inscrições = new ArrayList<>();
	}
	
	public String duração(){
		int dias = começo.periodoEmDias(fim);
		long minutos = começo.duraçãoEmMinutos(fim);
		return dias + " - " + minutos;
	}
	
	public void adicionarInscricao(Inscrição inscrição){
		this.inscrições.add(inscrição);
	}
	
	public boolean compararHorario(Atividade at){//true se está no mesmo horario
		boolean antes = começo.getHora().compareTo(at.getFim().getHora())==1;
		boolean depois = fim.getHora().compareTo(at.getComeço().getHora())==-1;
		return !antes && !depois;
	}
	
	private Agenda getFim() {
		return fim;
	}
	public int verificarVagas(){
		return capacidade - inscrições.size();
	}
	
	public String getNome() {
		return nome;
	}

	public Agenda getComeço() {
		return começo;
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
