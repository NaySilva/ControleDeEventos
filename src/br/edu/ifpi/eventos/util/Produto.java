package br.edu.ifpi.eventos.util;

import br.edu.ifpi.eventos.modelo.Inscricao;

public interface Produto {
	
	public void adicionarNoCarrinho(Inscricao inscricao) throws Exception;
	public double getPreco();
	public void setPreco(double valor);
	public TipoDeAtividade getTipo();
}
