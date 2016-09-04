package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.Observer;
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
	
	public Atividade(String nome, Agenda agenda, TipoDeAtividadeEnum tipo) {
		this.nome = nome;
		this.agenda = agenda;
		this.tipo = tipo;
		this.inscricoes = new ArrayList<>();
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
	}

	public Agenda getAgenda() {
		return agenda;
	}
	
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
		setNotificacao("A agenda foi modificada: " + this.agenda);
		notifyObservers();
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
		setNotificacao("Novo preço: " + preco);
		notifyObservers();
	}
	
	@Override
	public void setNotificacao(String mensagem) {
		notificacao = "Nova notificação da atividade " + this.nome + ":\n";
		notificacao += mensagem;
	}

	@Override
	public String toString() {
		return nome + " - " + tipo;
	}
	

	
	
	
	
	
	



}
