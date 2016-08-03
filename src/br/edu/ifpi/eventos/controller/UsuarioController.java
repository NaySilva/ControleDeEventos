package br.edu.ifpi.eventos.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpi.eventos.dao.JdbcUsuarioDao;
import br.edu.ifpi.eventos.modelo.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("novoUsuario")
	public String form(){
		return "usuario/cadastro";
	}
	
	@RequestMapping("adicionaUsuario")
	public String adiciona(Usuario usuario){
		System.out.println(usuario);
		JdbcUsuarioDao dao = new JdbcUsuarioDao();
		dao.adiciona(usuario);
		return "usuario/adicionado";
	}
}
