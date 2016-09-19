package br.edu.ifpi.eventos.modelo.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.excecoes.AtividadeNaoAptaParaItemException;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;

@Entity
@DiscriminatorValue("item_unico")
public class ItemUnico extends Item{
	
	@OneToOne
	private Atividade atividade;

	
	public ItemUnico(double preco, Atividade atividade) throws AtividadeNaoAptaParaItemException {
		super(preco);
		if(atividade.isPagavel()){
			this.atividade = atividade;
		}else{
			throw new AtividadeNaoAptaParaItemException();
		}
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
