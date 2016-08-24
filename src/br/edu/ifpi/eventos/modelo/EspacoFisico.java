package br.edu.ifpi.eventos.modelo;

import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;

public class EspacoFisico {
	
	private String descrição;
	private TipoEspacoFisico tipo;
	private int capacidade;
	private String endereco;
	
	private EspacoFisico pai;
	private List<EspacoFisico> filhos;
	private List<Inscricao> inscricoes;
	private List<Agenda> horarios;
	
	
	
	

}
