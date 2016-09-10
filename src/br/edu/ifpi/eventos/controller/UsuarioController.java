package br.edu.ifpi.eventos.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpi.eventos.modelo.usuario.Usuario;
import br.edu.ifpi.eventos.modelo.usuario.UsuarioDao;

@Transactional
@Controller
public class UsuarioController {
	@Autowired
	UsuarioDao dao;

	@RequestMapping("novoUsuario")
	public String form(){
		return "usuario/cadastro";
	}
	
	@RequestMapping("adicionaUsuario")
	public String adiciona(@Valid Usuario usuario, BindingResult result){
		if(result.hasErrors()){
			return "usuario/cadastro";
		}
		dao.adiciona(usuario);
		return "usuario/adicionado";
	}
	
	@RequestMapping("loginForm")
	public String loginForm(){
		return "usuario/formulario-login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario user, HttpSession session){
		Usuario usuario = dao.existeUsuario(user);
		if(usuario!=null){
			session.setAttribute("usuarioLogado", usuario);
			return "redirect:listaPerfis";
		}
		return "redirect:loginForm";
	}
	
}
