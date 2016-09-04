package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.Observer;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;
import br.edu.ifpi.eventos.util.Subject;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

@Entity
public class Evento extends Subject{
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToOne
	private Agenda agenda;
	@OneToMany(mappedBy="evento")
	private List<Inscricao> inscricoes;
	private Evento eventoPrincipal;
	private List<Evento> eventosSatelites;
	private TipoDeEventoEnum tipo;
	@OneToMany
	@JoinColumn(name="evento_id")
	private List<Atividade> atividades;
	@OneToOne
	private Agenda periodoDeInscricao;
	private List<Equipe> equipes;
	
	public Evento(String nome, Agenda agenda, TipoDeEventoEnum tipo) {
		this.nome = nome;
		this.agenda = agenda;
		this.tipo = tipo;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		this.eventosSatelites = new ArrayList<Evento>();
		verificarData(agenda);
	}
	
	public Evento(String nome, TipoDeEventoEnum tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		this.eventosSatelites = new ArrayList<Evento>();
	}

	public void verificarData(Agenda agenda) {
		Agenda noMomento = Agenda.noMomento;
		if (agenda.depoisDoFim(noMomento.getDiaFim(), noMomento.getHoraFim())){
			throw new IllegalArgumentException("Data Passada");
		}
	}
	
	public StatusDoEventoEnum verificarStatus(){
		StatusDoEventoEnum status;
		Agenda hoje = Agenda.noMomento;
		boolean entreOsDias = periodoDeInscricao.noMeio(hoje.getDiaFim(), hoje.getHoraFim());
		if (entreOsDias){
			status = StatusDoEventoEnum.InscricoesAbertas;
		}else if (agenda.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim())){
			status = StatusDoEventoEnum.Finalizado;
		}else{
			status = StatusDoEventoEnum.EmAndamento;
		}
		return status;
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
		limparNotificacao();
	}

	public void adicionarEventosSatelites(Evento evento){
		this.eventosSatelites.add(evento);
	}
	
	public Agenda getPeriodoDeInscricao() {
		return periodoDeInscricao;
	}

	public void setPeriodoDeInscricao(Agenda periodoDeInscricao) {
		this.periodoDeInscricao = periodoDeInscricao;
		setNotificacao("O periodo de inscrição mudou: " + this.periodoDeInscricao);
		notifyObservers();
	}
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
		setNotificacao("Nova atividade adicionada: " + atividade);
		notifyObservers();
	}
	
	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}
	
	public Evento comEventoPrincipal(Evento principal){
		this.eventoPrincipal = principal;
		principal.adicionarEventosSatelites(this);
		return this;
	}
	
	public List<Evento> getEventosSatelites(){
		return Collections.unmodifiableList(eventosSatelites);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNotificacao(String mensagem) {
		notificacao = "Nova notificação do evento " + this.nome + ":\n";
		notificacao += mensagem;
	}
	
	
	
}
