package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemKit extends Item{
	
	private List<ItemUnico> itens;
	
	public ItemKit(String descricao, double preco) {
		super(descricao, preco);
		this.itens = new ArrayList<ItemUnico>();
	}
	
	public void adicionarItemUnico(ItemUnico item){
		itens.add(item);
	}

	@Override
	public void adicionarNoCarrinho(Inscricao inscricao) throws Exception {
		inscricao.adicionarItem(this);
	}
	
	public List<ItemUnico> getItens() {
		return Collections.unmodifiableList(itens);
	}


}
