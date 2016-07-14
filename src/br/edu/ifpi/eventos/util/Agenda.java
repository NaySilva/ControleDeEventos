package br.edu.ifpi.eventos.util;

public class Agenda {
	private DataEHora começo;
	private DataEHora fim;
	
	public Agenda(DataEHora começo, DataEHora fim) {
		this.começo = começo;
		this.fim = fim;
	}
	
	public Agenda(DataEHora dia){
		this.fim = dia;
	}
	
	public boolean compararHorario(Agenda ag){
		boolean antes = antesDoComeço(ag.começo) && (antesDoComeço(ag.fim) || começo.equals(ag.fim));
		boolean depois = (depoisDoFim(ag.começo) || fim.equals(ag.começo)) && depoisDoFim(ag.fim);
		return antes || depois;
	}
	
	public boolean noMeio(DataEHora parte){
		return !antesDoComeço(parte) && !depoisDoFim(parte);
	}
	
	public boolean depoisDoFim(DataEHora parte){
		return (fim.getData().compareTo(parte.getData()) < 0) || (fim.getData().equals(parte.getData()) && fim.getHora().compareTo(parte.getHora())<0);
	}
	
	public boolean antesDoComeço(DataEHora parte){
		return começo.getData().compareTo(parte.getData())>0 || (começo.getData().equals(parte.getData()) && começo.getHora().compareTo(parte.getHora())>0);
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
