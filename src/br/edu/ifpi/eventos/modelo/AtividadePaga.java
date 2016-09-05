package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;

public class AtividadePaga extends Atividade implements Produto{

	private double preco;
	private List<Inscricao> inscricoes;

	public AtividadePaga(String nome, TipoDeAtividadeEnum tipo) {
		super(nome, tipo);
		this.inscricoes = new ArrayList<>();
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}
	
	@Override
	public double getPreco() {
		return preco;
	}

	@Override
	public void setPreco(double preco) {
		this.preco = preco;
		setNotificacao("Novo preço: " + preco);
		notifyObservers();
	}

	@Override
	public void adicionarNoCarrinho(Inscricao inscricao) throws Exception {
		inscricao.retricoesDeAtividade(this);
		inscricao.adicionarUmaAtividade(this);
		adicionarInscricao(inscricao);
	}
	

	public AtividadePaga emLocal(EspacoFisico local){
		setLocal(local);
		return this;
	}
	
	public AtividadePaga noHorario(Agenda agenda) throws HorarioIndisponivelException{
		setAgenda(agenda);
		return this;
	}
	
	

}
