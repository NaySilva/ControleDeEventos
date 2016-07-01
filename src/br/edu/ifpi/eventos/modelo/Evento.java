package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;

public class Evento extends Atividade {
	
	public Evento(String nome, Agenda horario) {
		super(nome, horario);
		// TODO Auto-generated constructor stub
	}

	public final static String EM_ANDAMENTO = "Em andamento";
	public final static String INSCRICOES = "Inscrições";
	public final static String FINALIZADA = "Finalizada"; 

	
	
	private Instituicao instituicao;
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	

}
