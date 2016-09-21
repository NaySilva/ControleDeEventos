package br.edu.ifpi.eventos.modelo.atividade;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;

public class AtividadeBuilder {
	
	public Atividade atividade;
	
	public AtividadeBuilder() {
		this.atividade = new Atividade();
	}
	
	public AtividadeBuilder comNome(String nome){
		this.atividade.setNome(nome);
		return this;
	}
	
	public AtividadeBuilder doTipo(TipoDeAtividade tipo){
		this.atividade.setTipo(tipo);
		return this;
	}
	
	public AtividadeBuilder emLocal(EspacoFisico local){
		this.atividade.setLocal(local);
		return this;
	}
	
	public AtividadeBuilder noHorario(Agenda agenda) throws HorarioIndisponivelException{
		this.atividade.setAgenda(agenda);
		return this;
	}
	
	public AtividadeBuilder pagavel(){
		this.atividade.setPagavel(true);
		return this;
	}
	
	public AtividadeBuilder naoPagavel(){
		this.atividade.setPagavel(false);
		return this;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

}
