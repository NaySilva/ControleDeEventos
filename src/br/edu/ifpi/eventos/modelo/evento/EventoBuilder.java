package br.edu.ifpi.eventos.modelo.evento;

import br.edu.ifpi.eventos.enums.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;

public class EventoBuilder {
	
	private Evento evento;
	
	public EventoBuilder() {
		this.evento = new Evento();
	}
	
	public EventoBuilder comNome(String nome){
		this.evento.nome = nome;
		return this;
	}
	
	public EventoBuilder doTipo(TipoDeEvento tipo){
		this.evento.tipo = tipo;
		return this;
	}
	
	public EventoBuilder comEventoPrincipal(Evento principal){
		this.evento.eventoPrincipal = principal;
		principal.adicionarEventosSatelites(this.evento);
		return this;
	}

	public EventoBuilder comInscricoesPara(Agenda agenda){
		this.evento.periodoDeInscricao = agenda;
		return this;
	}

	public EventoBuilder emLocal(EspacoFisico espacoFisico){
		this.evento.local = espacoFisico;
		return this;
	}
	
	public Evento getEvento() {
		return evento;
	}	

}
