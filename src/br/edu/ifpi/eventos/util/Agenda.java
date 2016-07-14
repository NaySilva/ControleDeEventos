package br.edu.ifpi.eventos.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
	private LocalDate diaCome�o;
	private LocalTime horaCome�o;
	private LocalDate diaFim;
	private LocalTime horaFim;
	
	public final static Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
	
	
	public Agenda(LocalDate diaFim, LocalTime horaFim) {
		this.diaFim = diaFim;
		this.horaFim = horaFim;
	}

	public Agenda(LocalDate diaCome�o, LocalTime horaCome�o, LocalDate diaFim, LocalTime horaFim) {
		this.diaCome�o = diaCome�o;
		this.horaCome�o = horaCome�o;
		this.diaFim = diaFim;
		this.horaFim = horaFim;
	}

	public boolean compararHorario(Agenda ag){
		boolean antes = antesDoCome�o(ag.diaCome�o, ag.horaCome�o) && (antesDoCome�o(ag.diaFim, ag.horaFim));
		boolean depois = (depoisDoFim(ag.diaCome�o, ag.horaCome�o) ) && depoisDoFim(ag.diaFim, ag.horaFim);
		return antes || depois;
	}
	
	public boolean noMeio(LocalDate data, LocalTime hora){
		return !antesDoCome�o(data, hora) && !depoisDoFim(data, hora);
	}
	
	public boolean depoisDoFim(LocalDate data, LocalTime hora){
		boolean diaAnterior = diaFim.compareTo(data) < 0;
		boolean mesmoDia = diaFim.equals(data);
		boolean horaAnterior = horaFim.compareTo(hora)<0;
		return diaAnterior || (mesmoDia && horaAnterior);
	}
	
	public boolean antesDoCome�o(LocalDate data, LocalTime hora){
		boolean diaPosterior = diaCome�o.compareTo(data)>0;
		boolean mesmoDia = diaCome�o.equals(data);
		boolean horaPosterior = horaCome�o.compareTo(hora)>0;
		return diaPosterior || (mesmoDia && horaPosterior);
	}

	public LocalDate getDiaCome�o() {
		return diaCome�o;
	}

	public LocalTime getHoraCome�o() {
		return horaCome�o;
	}

	public LocalDate getDiaFim() {
		return diaFim;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}
	
	

	

}
