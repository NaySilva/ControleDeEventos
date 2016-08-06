package br.edu.ifpi.eventos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class GenericJPADAO<T> implements GenericDAO<T>{
	
	@PersistenceContext
	private EntityManager manager;
	private Class<T> classe;
	
	public GenericJPADAO(Class<T> classe) {
		this.classe = classe;
	}

	@Override
	public void adiciona(T entidade) {
		manager.persist(entidade);
	}

	@Override
	public void remove(int id) {
		T entidade = manager.find(classe, id);
		manager.remove(entidade);
	}

	@Override
	public T buscaPorId(int id) {
		T entidade = manager.find(classe, id);
		return entidade;
	}

	@Override
	public List<T> lista() {
		TypedQuery<T> query = manager.createQuery("select c from " + this.classe.getSimpleName()+" c", this.classe);
		return query.getResultList();
	}

	@Override
	public void altera(T entidade) {
		manager.merge(entidade);
		
	}

}
