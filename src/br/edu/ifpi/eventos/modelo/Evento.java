package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public abstract class Evento extends Atividade {
	
	private Instituicao instituicao;
	private StatusDoEventoEnum status;
	private List<Atividade> atividades;
	
	public Evento(String nome, Agenda horario) {
		super(nome, horario);
		this.atividades = new ArrayList<Atividade>();
	}
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public StatusDoEventoEnum getStatus() {
		return status;
	}
	
	

}
