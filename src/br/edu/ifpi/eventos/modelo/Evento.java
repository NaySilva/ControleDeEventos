package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.StatusDoEventoEnum;
import br.edu.ifpi.eventos.util.TipoDeEventoEnum;

public class Evento {
	
	private String nome;
	private Agenda agenda;
	private double preco;
	private List<Inscricao> inscricoes;
	private int capacidade;
	private TipoDeEventoEnum tipo;
	private Instituicao instituicao;
	private List<Atividade> atividades;
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
	
	public Instituicao getInstituicao(){
		return instituicao;
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
