package br.edu.ifpi.eventos.util;

import br.edu.ifpi.eventos.modelo.Inscricao;

public abstract class Produto {
	
	public abstract void adicionarNoCarrinho(Inscricao inscricao) throws Exception;
	public abstract double getPreco();
	public abstract void setPreco(double valor);
	public abstract TipoDeAtividade getTipo();
}
