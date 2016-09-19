package br.edu.ifpi.eventos.modelo.perfil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

@Repository
public class PerfilDao extends GenericJPADAO<Perfil>{
	
	
	public PerfilDao() {
		super(Perfil.class);
	}

	@PersistenceContext
	EntityManager manager;

	public List<Perfil> lista(Usuario usuario) {
		TypedQuery<Perfil> query = manager.createQuery("from Perfil p where usuario_id = :paramUsuario", Perfil.class);
		query.setParameter("paramUsuario", usuario.getId());
		return query.getResultList();
	}

}
 