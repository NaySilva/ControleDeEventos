package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.pessoa.Pessoa;
import br.edu.ifpi.eventos.modelo.pessoa.PessoaDao;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;
import br.edu.ifpi.eventos.modelo.usuario.UsuarioDao;

public class DaoTeste {
	

	
	@Test
	public void adicionar_Pessoa() {
		PessoaDao dao = new PessoaDao();
		Pessoa pessoa = new Pessoa("Maria");
		pessoa.setCpf("035.123.113-09");
		pessoa.setEmail("maria@gmail.com");
		dao.adiciona(pessoa);
	}
	
	@Test
	public void remover_Pessoa(){
		PessoaDao dao = new PessoaDao();
		dao.remove(dao.ultimoCodigo());
	}
	
	@Test
	public void buscar_Pessoa(){
		adicionar_Pessoa();
		PessoaDao dao = new PessoaDao();
		Pessoa pessoa = dao.buscaPorId(dao.ultimoCodigo());
		assertEquals("Maria", pessoa.getNome());
	}
	
	@Test
	public void adicionar_Usuario_Com_Chave_Estrageira_Pessoa(){
		UsuarioDao daoU = new UsuarioDao();
		adicionar_Pessoa();
		PessoaDao daoP = new PessoaDao();
		Usuario usuario = new Usuario(daoP.buscaPorId(daoP.ultimoCodigo()));
		usuario.setLogin("mar");
		usuario.setSenha("123");
		daoU.adiciona(usuario);
	}

}
