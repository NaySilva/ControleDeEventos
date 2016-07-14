package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.DataEHora;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;

public abstract class Evento extends Atividade {
	
	private Instituicao instituicao;
	private List<Atividade> atividades;
	private Agenda periodoDeInscri��o;
	private String local;
	
	public Evento(String nome, Agenda agenda) {
		super(nome, agenda);
		this.atividades = new ArrayList<Atividade>();
		verificarData(agenda);
	}

	public void verificarData(Agenda agenda) {
		Agenda hoje = new Agenda(new DataEHora(LocalDate.now(), LocalTime.now()));
		if (agenda.depoisDoFim(hoje.getFim())){
			throw new IllegalArgumentException("Data Passada");
		}
	}
	
	public StatusDoEventoEnum verificarStatus(){
		StatusDoEventoEnum status;
		Agenda hoje = new Agenda(new DataEHora(LocalDate.now(), LocalTime.now()));
		boolean entreOsDias = periodoDeInscri��o.noMeio(hoje.getFim());
		if (entreOsDias){
			status = StatusDoEventoEnum.Inscri��esAbertas;
		}else if (super.getAgenda().depoisDoFim(hoje.getFim())){
			status = StatusDoEventoEnum.Finalizado;
		}else{
			status = StatusDoEventoEnum.EmAndamento;
		}
		return status;
	}

	public Agenda getPeriodoDeInscri��o() {
		return periodoDeInscri��o;
	}

	public void setPeriodoDeInscri��o(Agenda periodoDeInscri��o) {
		this.periodoDeInscri��o = periodoDeInscri��o;
	}
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	public Instituicao getInstitui��o(){
		return instituicao;
	}
	
	public String getLocal(){
		return local;
	}
	
	

}
