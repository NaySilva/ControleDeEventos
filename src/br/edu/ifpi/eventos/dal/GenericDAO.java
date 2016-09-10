package br.edu.ifpi.eventos.dal;

import java.util.List;

public interface GenericDAO<T> {
	
	void adiciona(T entidade);
	
	void remove(Long id);
	
	T buscaPorId(Long id);
	
	List<T> lista();
	
	void altera(T entidade);
	

}
