package br.edu.ifpi.eventos.util;

public class Agenda {
	private DataEHora come�o;
	private DataEHora fim;
	
	public Agenda(DataEHora come�o, DataEHora fim) {
		this.come�o = come�o;
		this.fim = fim;
	}
	
	public Agenda(DataEHora dia){
		this.fim = dia;
	}
	
	public boolean compararHorario(Agenda ag){
		boolean antes = antesDoCome�o(ag.come�o) && (antesDoCome�o(ag.fim) || come�o.equals(ag.fim));
		boolean depois = (depoisDoFim(ag.come�o) || fim.equals(ag.come�o)) && depoisDoFim(ag.fim);
		return antes || depois;
	}
	
	public boolean noMeio(DataEHora parte){
		return !antesDoCome�o(parte) && !depoisDoFim(parte);
	}
	
	public boolean depoisDoFim(DataEHora parte){
		return (fim.getData().compareTo(parte.getData()) < 0) || (fim.getData().equals(parte.getData()) && fim.getHora().compareTo(parte.getHora())<0);
	}
	
	public boolean antesDoCome�o(DataEHora parte){
		return come�o.getData().compareTo(parte.getData())>0 || (come�o.getData().equals(parte.getData()) && come�o.getHora().compareTo(parte.getHora())>0);
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
