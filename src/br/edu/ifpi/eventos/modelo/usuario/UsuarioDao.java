package br.edu.ifpi.eventos.modelo.usuario;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class UsuarioDao extends GenericJPADAO<Usuario>{
	
	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public Usuario existeUsuario(Usuario u){
		Query query = manager.createQuery("from Usuario where login = :paramLogin and senha = :paramSenha", Usuario.class);
		query.setParameter("paramLogin", u.getLogin());
		query.setParameter("paramSenha", u.getSenha());
		return query.getResultList().isEmpty() ? null : (Usuario)query.getSingleResult();
	}
	

}
