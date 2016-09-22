package br.edu.ifpi.eventos.modelo.atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	protected String nome;
	@OneToOne
	protected Agenda agenda;
	@Transient
	protected Evento evento;
	protected TipoDeAtividade tipo;
	@OneToOne
	protected EspacoFisico local;
	@OneToMany(mappedBy="atividade")
	private List<Responsabilidade> responsaveis;
	protected boolean pagavel;
	
	public Atividade() {
		this.responsaveis = new ArrayList<Responsabilidade>();
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
	
	public void setTipo(TipoDeAtividade tipo) {
		this.tipo = tipo;
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
	
}
