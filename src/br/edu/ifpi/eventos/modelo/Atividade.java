package br.edu.ifpi.eventos.modelo;

import java.util.List;


import br.edu.ifpi.eventos.util.Agenda;

public class Atividade {
	
	private String nome;
	private Agenda agenda;
	private double preco;
	private List<Inscricao> inscrições;
	private int capacidade;
	
	public Atividade(String nome, Agenda horario) {
		this.nome = nome;
		this.agenda = horario;
	}
	
	public boolean compararHorario(Atividade at){
		return agenda.equals(at.agenda);
	}
	
	public int verificarVagas(){
		return capacidade - inscrições.size();
	}
	
	public String getNome() {
		return nome;
	}

	public Agenda getAgenda() {
		return agenda;
	}



}
