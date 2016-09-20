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
	
	public Evento(){}
	
	public Evento(String nome, TipoDeEvento tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.status = StatusDoEvento.EmAndamento;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		this.eventosSatelites = new ArrayList<Evento>();
	}
	
	public Evento comEventoPrincipal(Evento principal){
		this.eventoPrincipal = principal;
		principal.adicionarEventosSatelites(this);
		return this;
	}
	
	public Evento comInscricoesPara(Agenda agenda){
		this.periodoDeInscricao = agenda;
		return this;
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
	
	public List<Atividade> getAtividadesC() {
		return Collections.unmodifiableList(atividades);
	}
	
	protected List<Atividade> getAtividades(){
		return atividades;
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
	
	public List<Atividade> mostrarAtividadesOrdenadasPorAgenda(){
		List<Agenda> agendas = new ArrayList<Agenda>();
		List<Atividade> atividadesOrdernadas = new ArrayList<Atividade>();
		for (Atividade at : atividades) {
			agendas.add(at.getAgenda());
		}
		while(!agendas.isEmpty()){
			int proxima = posicaoDaProximaAtividade(agendas);
			atividadesOrdernadas.add(atividades.get(proxima));
			agendas.remove(proxima);
		}
		return atividadesOrdernadas;
	}

	public int posicaoDaProximaAtividade(List<Agenda> agendas) {
		int pos = 0;
		for (int i = 0; i < agendas.size(); i++) {
			pos = i;
			for (Agenda agenda : agendas) {
				if (agendas.get(i).compareAgendaTo(agenda)>0){
					pos=0;
					break;
				}
			}
		}
		return pos;
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
	
	public void setTipo(String tipo) {
		this.tipo = TipoDeEvento.porValor(tipo);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
