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

import br.edu.ifpi.eventos.dal.PerfilDao;
import br.edu.ifpi.eventos.modelo.PerfilParticipante;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

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
	public String adiciona(@Valid PerfilParticipante perfil, BindingResult result){
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
	
	@RequestMapping("removePerfil")
	public String remove(Long id){
		dao.remove(id);
		return "redirect:listaPerfis";
	}
	
	@RequestMapping("mostraPerfil")
	public String mostra(Long id, Model model){
		model.addAttribute("perfil", dao.buscaPorId(id));
		System.out.println("kk"+dao.buscaPorId(id));
		return "perfil/mostra";
	}
	
	@RequestMapping("alteraPerfil")
	public String altera(PerfilParticipante perfil, Usuario usuario){
		//System.out.println(perfil);
		perfil.setUsuario(this.usuario);
		dao.altera(perfil);
		return "redirect:listaPerfis";
	}
	

}
