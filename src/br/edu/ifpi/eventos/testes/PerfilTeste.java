package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.equipe.Equipe;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.evento.EventoBuilder;
import br.edu.ifpi.eventos.modelo.evento.TipoDeEvento;
import br.edu.ifpi.eventos.modelo.perfil.PerfilOrganizador;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.perfil.TipoDeParticipacao;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

public class PerfilTeste {

	@Test
	public void criarEquipeDeUmEvento() {
		Usuario usu = new Usuario();
		PerfilOrganizador perfil = new PerfilOrganizador(usu);
		Usuario usu1 = new Usuario();
		Usuario usu2 = new Usuario();
		Evento ev = new EventoBuilder().comNome("ev").doTipo(TipoDeEvento.Simposio).getEvento();
		Equipe eq1 = new Equipe("limpeza", perfil, ev);
		eq1.adicionarPerfil(new PerfilOrganizador(usu1));
		eq1.adicionarPerfil(new PerfilOrganizador(usu2));
		assertEquals(true, eq1.getDono()==perfil && eq1.getOrganizadores().size()==2);
	}
	
	@Test
	public void Podera_Identificar_Tipo_De_Participante_No_Perfil_Participante(){
		Usuario usu = new Usuario();
		PerfilParticipante perfil = new PerfilParticipante(usu).comTipoDeParticipacao(TipoDeParticipacao.Estudante);
		assertEquals(TipoDeParticipacao.Estudante, perfil.getTipo());
	}
	
	
	

}
