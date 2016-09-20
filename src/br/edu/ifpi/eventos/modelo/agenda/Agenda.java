package br.edu.ifpi.eventos.modelo.agenda;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Agenda {
	@Id
	@GeneratedValue
	private Long id;
	private LocalDateTime comeco;
	private LocalDateTime fim;
	
	public final static Agenda noMomento = new Agenda(LocalDateTime.now());
	
	
	public Agenda(LocalDateTime horario) {
		this.comeco = horario;
		this.fim = horario;
	}

	public Agenda(LocalDateTime comeco, LocalDateTime fim) {
		this.comeco = comeco;
		this.fim = fim;
	}

	public boolean semInterferenciaNoHorario(Agenda ag){
		boolean antes = antesDoComeco(ag.comeco) && antesDoComeco(ag.fim);
		boolean depois = depoisDoFim(ag.comeco) && depoisDoFim(ag.fim);
		return antes || depois;	
	}
	
	public boolean dentroDoHorario(Agenda ag){
		return !antesDoComeco(ag.comeco) && !depoisDoFim(ag.fim);
	}

	public boolean antesDoComeco(LocalDateTime horario){
		return comeco.compareTo(horario)>0;
	}
	
	public boolean depoisDoFim(LocalDateTime horario){
		return fim.compareTo(horario)<0;
	}
	
	public int compareAgendaTo(Agenda agenda){
		if (comeco.equals(agenda.comeco)) return fim.compareTo(agenda.fim); 
		return (comeco.compareTo(agenda.comeco));
	}

	public LocalDateTime getComeco() {
		return comeco;
	}

	public LocalDateTime getFim() {
		return fim;
	}
	

}
