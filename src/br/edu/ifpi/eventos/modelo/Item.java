package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.util.Subject;


public abstract class Item extends Subject{
	
	protected String descricao;
	protected double preco;
	private List<Inscricao> inscricoes;
	
	public Item(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
		this.inscricoes = new ArrayList<Inscricao>();
	}

	public abstract void adicionarNoCarrinho(Inscricao inscricao) throws Exception;
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
		limparNotificacao();
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
		notificacao = "Nova notificação do item " + descricao + "/n";
		notificacao += mensagem;
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}
	

}
