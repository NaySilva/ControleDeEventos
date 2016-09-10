package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.util.TipoEspacoFisico;

@Entity
public class EspacoFisico {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private TipoEspacoFisico tipo;
	private int capacidade;
	private String endereco;
	@OneToOne
	private EspacoFisico localExterno;
	@OneToMany
	private List<EspacoFisico> locaisInternos;
	@OneToMany
	private List<Agenda> horarios;
	@OneToOne(mappedBy="local")
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
		local.setLocalExterno(this);
	}
	
	public void adicionarHorarios(Agenda agenda){
		this.horarios.add(agenda);
	}
	
	public void setLocalExterno(EspacoFisico localExterno) {
		this.localExterno = localExterno;
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
			if (ag.dentroDoHorario(agenda)) return true;
		}
		return false;
	}
	

}
