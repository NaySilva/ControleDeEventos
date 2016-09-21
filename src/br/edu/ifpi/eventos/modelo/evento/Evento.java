package br.edu.ifpi.eventos.modelo.evento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.equipe.Equipe;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.util.Observer;
import br.edu.ifpi.eventos.util.Subject;

@Entity
public class Evento extends Subject{
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToMany(mappedBy="evento")
	private List<Inscricao> inscricoes;
	@OneToOne
	private Evento eventoPrincipal;
	@ManyToMany
	private List<Evento> eventosSatelites;
	private TipoDeEvento tipo;
	private StatusDoEvento status;
	@OneToMany
	@JoinColumn(name="evento_id")
	private List<Atividade> atividades;
	@OneToOne
	private Agenda periodoDeInscricao;
	@OneToMany(mappedBy="evento")
	private List<Equipe> equipes;
	@OneToOne
	private EspacoFisico local;
	
	public Evento() {
		this.status = StatusDoEvento.EmAndamento;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		this.eventosSatelites = new ArrayList<Evento>();
	}
	
	public void verificarData(Agenda agenda) {
		if (agenda.depoisDoFim(Agenda.noMomento.getFim())){
			throw new IllegalArgumentException("Data Passada");
		}
	}
	
	public void verificarPeriodoDeInscricao(){
		Agenda hoje = Agenda.noMomento;
		if (periodoDeInscricao.dentroDoHorario(hoje)){
			status = StatusDoEvento.InscricoesAbertas;
		}
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
	
	public List<Atividade> atividadesOrdenadas(){
		List<Atividade> atividadesOrdenadas = this.atividades;
		atividadesOrdenadas.sort((a1, a2) -> a1.getAgenda().compareAgendaTo(a2.getAgenda()));
		return atividadesOrdenadas;
	}
	
	public void setStatus(StatusDoEvento status) {
		this.status = status;
		setNotificacao("Novo status do evento: " + status.toString());
		notifyObservers();
	}
	
	public TipoDeEvento getTipo() {
		return tipo;
	}
	
	public StatusDoEvento getStatus() {
		return status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTipo(TipoDeEvento tipo) {
		this.tipo = tipo;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setEventoPrincipal(Evento principal) {
		this.eventoPrincipal = principal;		
	}

	public void setLocal(EspacoFisico espacoFisico) {
		this.local = espacoFisico;
		
	}
	
}
