package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;


import br.edu.ifpi.eventos.util.Agenda;

public abstract class Atividade {
	
	private String nome;
	private Agenda agenda;
	private double preco;
	private List<Inscri��o> inscri��es;
	private int capacidade;
	
	public Atividade(String nome, Agenda horario) {
		this.nome = nome;
		this.agenda = horario;
		this.inscri��es = new ArrayList<>();
	}
	
	public void adicionarInscricao(Inscri��o inscri��o){
		this.inscri��es.add(inscri��o);
	}
	
	public boolean compararHorario(Atividade at){
		return agenda.equals(at.agenda);
	}
	
	public int verificarVagas(){
		return capacidade - inscri��es.size();
	}
	
	public String getNome() {
		return nome;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	



}
