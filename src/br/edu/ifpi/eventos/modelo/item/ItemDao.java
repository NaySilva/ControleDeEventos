package br.edu.ifpi.eventos.modelo.item;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

public class ItemDao extends GenericJPADAO<Item>{

	public ItemDao() {
		super(Item.class);
	}

}
