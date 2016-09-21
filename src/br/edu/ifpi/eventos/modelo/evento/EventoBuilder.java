package br.edu.ifpi.eventos.modelo.evento;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;

public class EventoBuilder {
	
	private Evento evento;
	
	public EventoBuilder() {
		this.evento = new Evento();
	}
	
	public EventoBuilder comNome(String nome){
		this.evento.setNome(nome);
		return this;
	}
	
	public EventoBuilder doTipo(TipoDeEvento tipo){
		this.evento.setTipo(tipo);
		return this;
	}
	
	public EventoBuilder comEventoPrincipal(Evento principal){
		this.evento.setEventoPrincipal(principal);
		principal.adicionarEventosSatelites(this.evento);
		return this;
	}

	public EventoBuilder comInscricoesPara(Agenda agenda){
		this.evento.setPeriodoDeInscricao(agenda);
		return this;
	}

	public EventoBuilder emLocal(EspacoFisico espacoFisico){
		this.evento.setLocal(espacoFisico);
		return this;
	}
	
	public Evento getEvento() {
		return evento;
	}	

}
