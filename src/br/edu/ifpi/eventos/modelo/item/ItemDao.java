package br.edu.ifpi.eventos.modelo.item;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class ItemDao extends GenericJPADAO<Item>{

	public ItemDao() {
		super(Item.class);
	}

}
