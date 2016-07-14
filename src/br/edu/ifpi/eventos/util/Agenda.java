package br.edu.ifpi.eventos.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
	private LocalDate diaComeço;
	private LocalTime horaComeço;
	private LocalDate diaFim;
	private LocalTime horaFim;
	
	public final static Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
	
	
	public Agenda(LocalDate diaFim, LocalTime horaFim) {
		this.diaFim = diaFim;
		this.horaFim = horaFim;
	}

	public Agenda(LocalDate diaComeço, LocalTime horaComeço, LocalDate diaFim, LocalTime horaFim) {
		this.diaComeço = diaComeço;
		this.horaComeço = horaComeço;
		this.diaFim = diaFim;
		this.horaFim = horaFim;
	}

	public boolean compararHorario(Agenda ag){
		boolean antes = antesDoComeço(ag.diaComeço, ag.horaComeço) && (antesDoComeço(ag.diaFim, ag.horaFim));
		boolean depois = (depoisDoFim(ag.diaComeço, ag.horaComeço) ) && depoisDoFim(ag.diaFim, ag.horaFim);
		return antes || depois;
	}
	
	public boolean noMeio(LocalDate data, LocalTime hora){
		return !antesDoComeço(data, hora) && !depoisDoFim(data, hora);
	}
	
	public boolean depoisDoFim(LocalDate data, LocalTime hora){
		boolean diaAnterior = diaFim.compareTo(data) < 0;
		boolean mesmoDia = diaFim.equals(data);
		boolean horaAnterior = horaFim.compareTo(hora)<0;
		return diaAnterior || (mesmoDia && horaAnterior);
	}
	
	public boolean antesDoComeço(LocalDate data, LocalTime hora){
		boolean diaPosterior = diaComeço.compareTo(data)>0;
		boolean mesmoDia = diaComeço.equals(data);
		boolean horaPosterior = horaComeço.compareTo(hora)>0;
		return diaPosterior || (mesmoDia && horaPosterior);
	}

	public LocalDate getDiaComeço() {
		return diaComeço;
	}

	public LocalTime getHoraComeço() {
		return horaComeço;
	}

	public LocalDate getDiaFim() {
		return diaFim;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}
	
	

	

}
