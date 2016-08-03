package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

@Entity
public class Evento {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToOne
	private Agenda agenda;
	private double preco;
	@OneToMany(mappedBy="evento")
	private List<Inscricao> inscricoes;
	private int capacidade;
	private TipoDeEventoEnum tipo;
	@OneToMany
	@JoinColumn(name="evento_id")
	private List<Atividade> atividades;
	@OneToOne
	private Agenda periodoDeInscricao;
	
	public Evento(String nome, Agenda agenda, TipoDeEventoEnum tipo) {
		this.nome = nome;
		this.agenda = agenda;
		this.tipo = tipo;
		this.atividades = new ArrayList<Atividade>();
		this.inscricoes = new ArrayList<Inscricao>();
		verificarData(agenda);
	}

	public void verificarData(Agenda agenda) {
		Agenda noMomento = Agenda.noMomento;
		if (agenda.depoisDoFim(noMomento.getDiaFim(), noMomento.getHoraFim())){
			throw new IllegalArgumentException("Data Passada");
		}
	}
	
	public StatusDoEventoEnum verificarStatus(){
		StatusDoEventoEnum status;
		Agenda hoje = Agenda.noMomento;
		boolean entreOsDias = periodoDeInscricao.noMeio(hoje.getDiaFim(), hoje.getHoraFim());
		if (entreOsDias){
			status = StatusDoEventoEnum.InscricoesAbertas;
		}else if (agenda.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim())){
			status = StatusDoEventoEnum.Finalizado;
		}else{
			status = StatusDoEventoEnum.EmAndamento;
		}
		return status;
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
	}

	public Agenda getPeriodoDeInscricao() {
		return periodoDeInscricao;
	}

	public void setPeriodoDeInscricao(Agenda periodoDeInscricao) {
		this.periodoDeInscricao = periodoDeInscricao;
	}
	
	public void adicionarAtividade(Atividade atividade){
		atividades.add(atividade);
	}
	
	public List<Atividade> getAtividades() {
		return Collections.unmodifiableList(atividades);
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double valor) {
		this.preco = valor;
	}
	
	
	
	

}
