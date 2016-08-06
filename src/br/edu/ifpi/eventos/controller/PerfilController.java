package br.edu.ifpi.eventos.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import br.edu.ifpi.eventos.dao.PerfilDao;
import br.edu.ifpi.eventos.modelo.Perfil;
import br.edu.ifpi.eventos.modelo.Usuario;

@Transactional
@Controller
public class PerfilController {
	
	@Autowired
	PerfilDao dao;
	Usuario usuario;
	
	@RequestMapping("novoPerfil")
	public String form(){
		return "perfil/cadastro";
	}
	
	@RequestMapping("adicionaPerfil")
	public String adiciona(@Valid Perfil perfil, BindingResult result){
		if(result.hasErrors()) {
		    return "perfil/cadastro";
		}
		perfil.setUsuario(usuario);
		dao.adiciona(perfil);
		return "perfil/adicionado";
	}
	
	@RequestMapping("listaPerfis")
	public String lista(@SessionAttribute("usuarioLogado")Usuario user, Model model, HttpSession session){ 
		this.usuario = user;
		model.addAttribute("perfis", dao.lista(usuario));
		return "perfil/lista";
	}
	

}
