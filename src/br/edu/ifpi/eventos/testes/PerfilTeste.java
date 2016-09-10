package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.equipe.Equipe;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.perfil.PerfilOrganizador;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class PerfilTeste {

	@Test
	public void criarEquipeDeUmEvento() {
		Usuario usu = new Usuario();
		PerfilOrganizador perfil = new PerfilOrganizador(usu);
		Usuario usu1 = new Usuario();
		Usuario usu2 = new Usuario();
		Evento ev = new Evento("ev", TipoDeEvento.Simposio);
		Equipe eq1 = new Equipe("limpeza", perfil, ev);
		eq1.adicionarPerfil(new PerfilOrganizador(usu1));
		eq1.adicionarPerfil(new PerfilOrganizador(usu2));
		assertEquals(true, eq1.getDono()==perfil && eq1.getOrganizadores().size()==2);
	}
	
	
	

}
