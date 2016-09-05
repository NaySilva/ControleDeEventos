package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoEspacoFisico;

public class EspacoFisico {
	
	private String descricao;
	private TipoEspacoFisico tipo;
	private int capacidade;
	private String endereco;
	
	private EspacoFisico localExterno;
	private List<EspacoFisico> locaisInternos;
	private List<Agenda> horarios;
	private Atividade atividade;
	
	public EspacoFisico(String descricao, TipoEspacoFisico tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
		this.locaisInternos = new ArrayList<EspacoFisico>();
		this.horarios = new ArrayList<Agenda>();
	}
	
	public EspacoFisico comLocalExterno(EspacoFisico local){
		this.localExterno = local;
		local.adicionarLocalInterno(this);
		return this;
	}
	
	public void adicionarLocalInterno(EspacoFisico local){
		this.locaisInternos.add(local);
	}
	
	public boolean verificarCapacidade() {
		return !(atividade.getInscricoes().size() == capacidade);
	}

	public void adicionarHorarios(Agenda agenda){
		this.horarios.add(agenda);
	}
	
	public List<Agenda> getHorarios() {
		return Collections.unmodifiableList(horarios);
	}
	
	
	public List<EspacoFisico> getLocaisInternos() {
		return Collections.unmodifiableList(locaisInternos);
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public boolean disponivelNoHorario(Agenda agenda){
		for (Agenda ag : horarios) {
			return ag.dentroDoHorario(agenda);
		}
		return false;
	}
	

}
