package br.edu.ifpi.eventos.modelo.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
@Entity
@DiscriminatorValue("item_kit")
public class ItemKit extends Item{
	
	@ManyToMany
	private List<ItemUnico> itens;
	
	public ItemKit(double preco) {
		super(preco);
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
