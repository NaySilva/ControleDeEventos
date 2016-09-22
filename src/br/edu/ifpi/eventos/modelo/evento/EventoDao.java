package br.edu.ifpi.eventos.modelo.evento;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.edu.ifpi.eventos.dal.GenericJPADAO;

@Repository
public class EventoDao extends GenericJPADAO<Evento>{

	public EventoDao() {
		super(Evento.class);
	}
	
	public List<Evento> eventosParticipandoPorPerfil(Long id){
		TypedQuery<Evento> query = manager.createQuery("select e from Evento e, Inscricao i, PerfilParticipante p where e.id = i.evento.id and i.perfil.id = p.id and p.usuario.id = :paramId", Evento.class);
		query.setParameter("paramId", id);
		return query.getResultList();
	}
	
	public List<Evento> eventosOrganizandoPorPerfil(Long id){
		TypedQuery<Evento> query = manager.createQuery("select e from Evento e, Inscricao i, PerfilOrganizador p where e.id = i.evento.id and i.perfil.id = p.id and p.usuario.id = :paramId", Evento.class);
		query.setParameter("paramId", id);
		return query.getResultList();
	}

}
