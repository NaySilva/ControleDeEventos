package br.edu.ifpi.eventos.modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inscri��o {
	
	private Evento evento;
	private Usuario usuario;
	private List<Atividade> atividadesDesejadas = new ArrayList<Atividade>();
	private boolean pago;
	
	public Inscri��o(Evento evento, Usuario usuario){
		this.evento = evento;
		this.usuario = usuario;
		this.pago = false;
		evento.adicionarInscricao(this);
	}
	
	public void pagarInscri��o(double valor){
		CalcularConta cc = new CalcularConta(this); 
		double total = cc.calcularTotalComDesconto();
		this.pago = (total == valor) ? true : false;		
	}

	public String adicionarAtividadeDesejada(Atividade atividade){
		String deuCerto = "A atividade foi adicionada!";
		String deuErrado = "N�o foi possivel adicionar essa atividade";
		boolean semAtivRepetidas = !atividadesDesejadas.contains(atividade);
		boolean ativNoEvento = evento.getAtividades().contains(atividade);
		boolean horarioDisponivel = verificarDisponibilidade(atividade);
		if (horarioDisponivel && ativNoEvento && semAtivRepetidas && !isPago()){
			this.atividadesDesejadas.add(atividade);
			atividade.adicionarInscricao(this);
			return deuCerto;
		}
		return deuErrado;
	}

	public boolean verificarDisponibilidade(Atividade atividade) {
		if (atividadesDesejadas.isEmpty()) return true;
		for (Atividade at : atividadesDesejadas) {
			if (atividade.getAgenda().compararHorario(at.getAgenda()) ) return true;
		}
		return false;
	}
	public List<Atividade> getAtividadesDesejadas() {
		return Collections.unmodifiableList(atividadesDesejadas);
	}
	
	public Evento getEvento() {
		return evento;
	}

	public boolean isPago() {
		return pago;
	}
	
	
	
	
	

}
