package br.edu.ifpi.eventos.factore;

import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Produto;

public class InscricaoFactory {
	
	private Inscricao inscricao;
	
	public void inscriverEmProduto(Produto produto){
		this.inscricao.adicionarProduto(produto);
	}
	
	public void inscriverEmEventoCompleto(Evento evento){
		this.inscricao.adicionarTodasAsAtividades(evento.getAtividades());
	}
	

}
