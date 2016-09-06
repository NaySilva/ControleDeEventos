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
import br.edu.ifpi.eventos.util.StatusDoEvento;
import br.edu.ifpi.eventos.util.Subject;
import br.edu.ifpi.eventos.util.TipoDeEvento;

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
	private TipoDeEvento tipo;
	@OneToMany
	@JoinColumn(name="evento_id")
	private List<Atividade> atividades;
	@OneToOne
	private Agenda periodoDeInscricao;
	private List<Equipe> equipes;
	
	public Evento(String nome, Agenda agenda, TipoDeEvento tipo) {
		this.nome = nome;
		this.agenda = agenda;
		this.tipo = tipo;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		this.eventosSatelites = new ArrayList<Evento>();
		verificarData(agenda);
	}
	
	public Evento(String nome, TipoDeEvento tipo) {
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
	
	public StatusDoEvento verificarStatus(){
		StatusDoEvento status;
		Agenda hoje = Agenda.noMomento;
		boolean entreOsDias = periodoDeInscricao.noMeio(hoje.getDiaFim(), hoje.getHoraFim());
		if (entreOsDias){
			status = StatusDoEvento.InscricoesAbertas;
		}else if (agenda.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim())){
			status = StatusDoEvento.Finalizado;
		}else{
			status = StatusDoEvento.EmAndamento;
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
	
	
	
}
