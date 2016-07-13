package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.eventos.util.DataEHora;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public abstract class Evento extends Atividade {
	
	private Instituicao instituicao;
	private List<Atividade> atividades;
	private Agenda periodoDeInscrição;
	
	public Evento(String nome, Agenda agenda) {
		super(nome, agenda);
		this.atividades = new ArrayList<Atividade>();
	}
	
	public StatusDoEventoEnum verificarStatus(){
		StatusDoEventoEnum status;
		Agenda hoje = new Agenda(new DataEHora(LocalDate.now(), LocalTime.now()));
		boolean entreOsDias = periodoDeInscrição.compararDias(hoje) && periodoDeInscrição.compararHorario(hoje);
		boolean antesDoDiaDoEvento = super.getAgenda().compararDias(hoje) && super.getAgenda().compararHorario(hoje);
		if (entreOsDias){
			status = StatusDoEventoEnum.InscriçõesAbertas;
		}else if (!antesDoDiaDoEvento){
			status = StatusDoEventoEnum.Finalizado;
		}else{
			status = StatusDoEventoEnum.EmAndamento;
		}
		return status;
	}

	public Agenda getPeriodoDeInscrição() {
		return periodoDeInscrição;
	}

	public void setPeriodoDeInscrição(Agenda periodoDeInscrição) {
		this.periodoDeInscrição = periodoDeInscrição;
	}
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	public Instituicao getInstituição(){
		return instituicao;
	}

	
	

}
