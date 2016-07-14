package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public abstract class Evento extends Atividade {
	
	private Instituicao instituicao;
	private List<Atividade> atividades;
	private Agenda periodoDeInscrição;
	
	public Evento(String nome, Agenda agenda) {
		super(nome, agenda);
		this.atividades = new ArrayList<Atividade>();
		verificarData(agenda);
	}

	public void verificarData(Agenda agenda) {
		Agenda hoje = Agenda.hoje;
		if (agenda.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim())){
			throw new IllegalArgumentException("Data Passada");
		}
	}
	
	public StatusDoEventoEnum verificarStatus(){
		StatusDoEventoEnum status;
		Agenda hoje = Agenda.hoje;
		boolean entreOsDias = periodoDeInscrição.noMeio(hoje.getDiaFim(), hoje.getHoraFim());
		if (entreOsDias){
			status = StatusDoEventoEnum.InscriçõesAbertas;
		}else if (super.getAgenda().depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim())){
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
