package br.edu.ifpi.eventos.modelo.atividade;

import br.edu.ifpi.eventos.enums.TipoDeAtividade;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;

public class AtividadeBuilder {
	
	public Atividade atividade;
	
	public AtividadeBuilder() {
		this.atividade = new Atividade();
	}
	
	public AtividadeBuilder comNome(String nome){
		this.atividade.nome = nome;
		return this;
	}
	
	public AtividadeBuilder doTipo(TipoDeAtividade tipo){
		this.atividade.tipo = tipo;
		return this;
	}
	
	public AtividadeBuilder emLocal(EspacoFisico local){
		this.atividade.local = local;
		return this;
	}
	
	public AtividadeBuilder doEvento(Evento evento){
		this.atividade.evento = evento;
		return this;
	}
	
	public AtividadeBuilder noHorario(Agenda agenda) throws HorarioIndisponivelException{
		if (this.atividade.local.disponivelNoHorario(agenda)){
			this.atividade.agenda = agenda;
			return this;
		}else{
			throw new HorarioIndisponivelException();
		}
	}
	
	public AtividadeBuilder pagavel(){
		this.atividade.pagavel = true;
		return this;
	}
	
	public AtividadeBuilder naoPagavel(){
		this.atividade.pagavel = false;
		return this;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

}
