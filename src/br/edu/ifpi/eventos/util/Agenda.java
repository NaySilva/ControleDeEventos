package br.edu.ifpi.eventos.util;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Agenda {
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate diaComeco;
	private LocalTime horaComeco;
	private LocalDate diaFim;
	private LocalTime horaFim;
	
	public final static Agenda noMomento = new Agenda(LocalDate.now(), LocalTime.now());
	
	
	public Agenda(LocalDate dia, LocalTime hora) {
		this.diaComeco = dia;
		this.horaComeco = hora;
		this.diaFim = dia;
		this.horaFim = hora;
	}

	public Agenda(LocalDate diaComeco, LocalTime horaComeco, LocalDate diaFim, LocalTime horaFim) {
		this.diaComeco = diaComeco;
		this.horaComeco = horaComeco;
		this.diaFim = diaFim;
		this.horaFim = horaFim;
	}

	public boolean compararHorario(Agenda ag){
		boolean antes = antesDoComeco(ag.diaComeco, ag.horaComeco) && antesDoComeco(ag.diaFim, ag.horaFim);
		boolean depois = depoisDoFim(ag.diaComeco, ag.horaComeco) && depoisDoFim(ag.diaFim, ag.horaFim);
		return antes || depois;	
	}
	
	public boolean dentroDoHorario(Agenda ag){
		return noMeio(ag.diaComeco, ag.horaComeco) && noMeio(ag.diaFim, ag.horaFim);
	}
	
	public boolean noMeio(LocalDate data, LocalTime hora){
		return !antesDoComeco(data, hora) && !depoisDoFim(data, hora);
	}
	
	public boolean depoisDoFim(LocalDate data, LocalTime hora){
		boolean diaAnterior = diaFim.compareTo(data) < 0;
		boolean mesmoDia = diaFim.equals(data);
		boolean horaAnterior = horaFim.compareTo(hora)<0;
		return diaAnterior || (mesmoDia && horaAnterior);
	}
	
	public int compareAgendaTo(Agenda agenda){
		if (diaComeco.equals(agenda.diaComeco)) return horaComeco.compareTo(agenda.horaComeco);
		return (diaComeco.compareTo(agenda.diaComeco));
	}
	
	public boolean antesDoComeco(LocalDate data, LocalTime hora){
		boolean diaPosterior = diaComeco.compareTo(data)>0;
		boolean mesmoDia = diaComeco.equals(data);
		boolean horaPosterior = horaComeco.compareTo(hora)>0;
		return diaPosterior || (mesmoDia && horaPosterior);
	}

	public LocalDate getDiaComeco() {
		return diaComeco;
	}

	public LocalTime getHoraComeco() {
		return horaComeco;
	}

	public LocalDate getDiaFim() {
		return diaFim;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}
	
	

	

}
