package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.DataEHora;

public class Agenda {

	private DataEHora come�o;
	private DataEHora fim;
	
	public Agenda(DataEHora come�o, DataEHora fim) {
		this.come�o = come�o;
		this.fim = fim;
	}
	
	public Agenda(DataEHora dia){
		this.come�o = dia;
	}
	
	public boolean compararDias(Agenda ag){
		boolean antes = come�o.getData().compareTo(ag.getCome�o().getData())==1;
		boolean depois = fim.getData().compareTo(ag.getCome�o().getData())==-1;
		return !antes && !depois;
	}
	

	public boolean compararHorario(Agenda ag){
		boolean antes = come�o.getHora().compareTo(ag.getCome�o().getHora())==1;
		boolean depois = fim.getHora().compareTo(ag.getCome�o().getHora())==-1;
		return !antes && !depois;
	}
	
	public String dura��o(){
		int dias = come�o.periodoEmDias(fim);
		long minutos = come�o.dura��oEmMinutos(fim);
		return dias + " - " + minutos;
	}

	public DataEHora getCome�o() {
		return come�o;
	}

	public DataEHora getFim() {
		return fim;
	}
	
	
	

}
