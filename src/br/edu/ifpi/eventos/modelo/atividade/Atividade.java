package br.edu.ifpi.eventos.modelo.atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.espacofisico.EspacoFisico;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
import br.edu.ifpi.eventos.util.Subject;
@Entity
public class Atividade extends Subject {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToOne
	private Agenda agenda;
	@Transient
	private Evento evento;
	private TipoDeAtividade tipo;
	@OneToOne
	private EspacoFisico local;
	@OneToMany(mappedBy="atividade")
	private List<Responsabilidade> responsaveis;
	private boolean pagavel;
	private boolean realizado;
	
	Atividade(){}
	
	public Atividade(String nome, TipoDeAtividade tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.pagavel=true;
		this.responsaveis = new ArrayList<Responsabilidade>();
	}
	
	public Atividade emLocal(EspacoFisico local){
		setLocal(local);
		return this;
	}
	
	public Atividade noHorario(Agenda agenda) throws HorarioIndisponivelException{
		setAgenda(agenda);
		return this;
	}

	public Agenda getAgenda() {
		return agenda;
	}
	
	public void setAgenda(Agenda agenda) throws HorarioIndisponivelException {
		if (local.disponivelNoHorario(agenda)){
			this.agenda = agenda;
			setNotificacao("A agenda foi modificada: " + this.agenda);
			notifyObservers();
		}else{
			throw new HorarioIndisponivelException();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public TipoDeAtividade getTipo() {
		return tipo;
	}
	
	@Override
	public void setNotificacao(String mensagem) {
		notificacao = "Nova notificação da atividade " + this.nome + ":\n";
		notificacao += mensagem;
	}

	public void setLocal(EspacoFisico local) {
		this.local = local;
	}
	
	@Override
	public String toString() {
		return nome + " - " + tipo;
	}
	
	public EspacoFisico getLocal() {
		return local;
	}
	
	public void adicionarResponsavel(Responsabilidade resposavel){
		this.responsaveis.add(resposavel);
	}
	
	public List<Responsabilidade> getResponsaveis() {
		return Collections.unmodifiableList(responsaveis);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTipo(String tipo) {
		this.tipo = TipoDeAtividade.porValor(tipo);
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public boolean isPagavel() {
		return pagavel;
	}
	
	public void setPagavel(boolean pagavel) {
		this.pagavel = pagavel;
	}
	
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	
	public boolean isRealizado() {
		return realizado;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
