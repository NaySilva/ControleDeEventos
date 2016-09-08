package br.edu.ifpi.eventos.modelo;

public class ItemUnico extends Item{
	
	Atividade atividade;

	
	public ItemUnico(String descricao, double preco, Atividade atividade) {
		super(descricao, preco);
		this.atividade = atividade;
	}

	public void adicionarNoCarrinho(Inscricao inscricao) throws Exception {
		inscricao.retricoesDeAtividade(this);
		inscricao.adicionarUmItem(this);
		adicionarInscricao(inscricao);
	}

	@Override
	public void setPreco(double valor) {
		super.preco = valor;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

}
