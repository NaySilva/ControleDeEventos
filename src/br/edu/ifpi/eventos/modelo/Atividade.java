package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.Subject;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;
@Entity
public class Atividade extends Subject implements Produto {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private double preco;
	@OneToOne
	private Agenda agenda;
	private TipoDeAtividadeEnum tipo;
	@ManyToMany
	private List<Inscricao> inscricoes;
	private EspacoFisico local;
	private List<Responsavel> responsaveis;
	private boolean realizado;
	
	public Atividade(String nome, TipoDeAtividadeEnum tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.inscricoes = new ArrayList<>();
	}
	
	public Atividade emLocal(EspacoFisico local){
		setLocal(local);
		return this;
	}
	
	public Atividade noHorario(Agenda agenda){
		setAgenda(agenda);
		return this;
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
	}

	public Agenda getAgenda() {
		return agenda;
	}
	
	public void setAgenda(Agenda agenda) {
		if (local.disponivelNoHorario(agenda)){
			this.agenda = agenda;
			setNotificacao("A agenda foi modificada: " + this.agenda);
			notifyObservers();
		}else{
			new HorarioIndisponivelException().printStackTrace();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public TipoDeAtividadeEnum getTipo() {
		return tipo;
	}

	@Override
	public void adicionarNoCarrinho(Inscricao inscricao) throws Exception {
		inscricao.retricoesDeAtividade(this);
		inscricao.adicionarUmaAtividade(this);
		adicionarInscricao(inscricao);
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
		setNotificacao("Novo pre�o: " + preco);
		notifyObservers();
	}
	
	@Override
	public void setNotificacao(String mensagem) {
		notificacao = "Nova notifica��o da atividade " + this.nome + ":\n";
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
	
	



}
