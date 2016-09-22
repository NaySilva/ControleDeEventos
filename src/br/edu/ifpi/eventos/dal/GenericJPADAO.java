package br.edu.ifpi.eventos.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class GenericJPADAO<T> implements GenericDAO<T>{
	
	protected EntityManager manager;
	private Class<T> classe;
	
	public GenericJPADAO(Class<T> classe) {
		this.classe = classe;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-de-eventos");
		manager = emf.createEntityManager();
	}

	@Override
	public void adiciona(T entidade) {
		manager.getTransaction().begin();
		manager.persist(entidade);
		manager.getTransaction().commit();
	}

	@Override
	public void remove(Long id) {
		T entidade = manager.find(classe, id);
		manager.getTransaction().begin();
		manager.remove(entidade);
		manager.getTransaction().commit();
	}

	@Override
	public T buscaPorId(Long id) {
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
		manager.getTransaction().begin();
		manager.merge(entidade);
		manager.getTransaction().commit();
	}
	
	public Long ultimoCodigo(){
		TypedQuery<Long> query = manager.createQuery("select max(id) from " + this.classe.getSimpleName(), Long.class);
		return query.getSingleResult();
	}

}
