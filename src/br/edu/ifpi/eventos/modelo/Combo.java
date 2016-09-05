package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;

public class Combo implements Produto{

	private List<AtividadePaga> atividades;
	public double preco;
	
	public Combo() {
		this.atividades = new ArrayList<>();
	}
	
	public void adicionarAtividade(AtividadePaga at){
		at.setPreco(0);
		atividades.add(at);
	}
	
	@Override
	public void setPreco(double valor){
		this.preco = valor;
	}

	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}

	@Override
	public void adicionarNoCarrinho(Inscricao inscricao) {
		inscricao.adicionarTodasAsAtividades(atividades);
		
	}

	@Override
	public double getPreco() {
		return preco;
	}

	@Override
	public TipoDeAtividadeEnum getTipo() {
		return null;
	}
	
	
	
}
