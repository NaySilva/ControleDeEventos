package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.DataEHora;

public class Agenda {

	private DataEHora começo;
	private DataEHora fim;
	
	public Agenda(DataEHora começo, DataEHora fim) {
		this.começo = começo;
		this.fim = fim;
	}
	
	public Agenda(DataEHora dia){
		this.começo = dia;
	}
	
	public boolean compararDias(Agenda ag){
		boolean antes = começo.getData().compareTo(ag.getComeço().getData())==1;
		boolean depois = fim.getData().compareTo(ag.getComeço().getData())==-1;
		return !antes && !depois;
	}
	

	public boolean compararHorario(Agenda ag){
		boolean antes = começo.getHora().compareTo(ag.getComeço().getHora())==1;
		boolean depois = fim.getHora().compareTo(ag.getComeço().getHora())==-1;
		return !antes && !depois;
	}
	
	public String duração(){
		int dias = começo.periodoEmDias(fim);
		long minutos = começo.duraçãoEmMinutos(fim);
		return dias + " - " + minutos;
	}

	public DataEHora getComeço() {
		return começo;
	}

	public DataEHora getFim() {
		return fim;
	}
	
	
	

}
