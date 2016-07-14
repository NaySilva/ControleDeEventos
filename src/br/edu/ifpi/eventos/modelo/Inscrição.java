package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inscrição {
	
	private Evento evento;
	private Perfil perfil;
	private List<Atividade> atividadesDesejadas = new ArrayList<Atividade>();
	private Pagamento pagamento;
	
	public Inscrição(Evento evento, Perfil perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		evento.getInscrições().add(this);
		perfil.getInscrições().add(this);
	}

	public boolean adicionarAtividadeDesejada(Atividade atividade){
		boolean semAtivRepetidas = !atividadesDesejadas.contains(atividade);
		boolean ativNoEvento = evento.getAtividades().contains(atividade);
		boolean horarioDisponivel = verificarDisponibilidade(atividade);
		boolean naoEstaPago = !pagamento.isPago();
		if (horarioDisponivel && ativNoEvento && semAtivRepetidas && naoEstaPago){
			this.atividadesDesejadas.add(atividade);
			atividade.getInscrições().add(this);
			return true;
		}
		return false;
	}

	public boolean verificarDisponibilidade(Atividade atividade) {
		for (Atividade at : atividadesDesejadas) {
			if (!atividade.getAgenda().compararHorario(at.getAgenda())) return false;
		}
		return true;
	}
	public List<Atividade> getAtividadesDesejadas() {
		return atividadesDesejadas;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	
	
	
	
	
	

}