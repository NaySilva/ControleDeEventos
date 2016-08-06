package br.edu.ifpi.eventos.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void adiciona(T entidade);
	
	void remove(int id);
	
	T buscaPorId(int id);
	
	List<T> lista();
	
	void altera(T entidade);
	

}
