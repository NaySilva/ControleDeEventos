package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.TipoDeAtividadeEnum;

public interface Produto {
	
	public void adicionarNoCarrinho(Inscricao inscricao);
	public double getPreco();
	public void setPreco(double valor);
	public TipoDeAtividadeEnum getTipo();
}
