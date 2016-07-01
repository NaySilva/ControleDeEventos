package br.edu.ifpi.eventos.teste;


import java.text.ParseException;
import java.util.Date;

import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscricao;
import br.edu.ifpi.eventos.modelo.Instituicao;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.Semana;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.Agenda;

public class EventoTeste {
	
	public static void main(String[] args) throws ParseException {
		Usuario u = new Usuario();
		
		Instituicao ifpi = new Instituicao("IFPI");
		
		Agenda a1 = new Agenda("01072016", "1000");
		Agenda a2 = new Agenda("sfsd6", "121230");
		Atividade p = new Palestra("Palestra", a1);
		Atividade mc = new Minicurso("Minicurso", a2);
		
		Evento ev = new Semana("Semana de quimica", a1);
		ev.adicionarAtividade(p);
		ev.adicionarAtividade(mc);
		
		Inscricao ins = new Inscricao(ev, u);
		System.out.println(ins.adicionarAtividadeDesejada(p));

		System.out.println(ins.adicionarAtividadeDesejada(mc));
		
		System.out.println(p.getAgenda());
		
		System.out.println(mc.getAgenda());
		
	}

}
