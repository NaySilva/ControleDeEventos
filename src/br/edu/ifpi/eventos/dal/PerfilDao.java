package br.edu.ifpi.eventos.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.modelo.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

@Repository
public class PerfilDao extends GenericJPADAO<PerfilParticipante>{
	
	public PerfilDao() {
		super(PerfilParticipante.class);
	}

	@PersistenceContext
	EntityManager manager;

	public List<PerfilParticipante> lista(Usuario usuario) {
		TypedQuery<PerfilParticipante> query = manager.createQuery("from Perfil p where usuario_id = :paramUsuario", PerfilParticipante.class);
		query.setParameter("paramUsuario", usuario.getId());
		return query.getResultList();
	}

}
 