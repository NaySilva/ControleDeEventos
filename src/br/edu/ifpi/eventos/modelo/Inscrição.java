package br.edu.ifpi.eventos.modelo;


import java.util.ArrayList;
import java.util.List;

public class Inscrição {
	
	private Evento evento;
	private Usuario usuario;
	private List<Atividade> atividadesDesejadas = new ArrayList<Atividade>();
	private boolean pago;
	
	public Inscrição(Evento evento, Usuario usuario) {
		this.evento = evento;
		this.usuario = usuario;
	}
	public Inscrição() {
		// TODO Auto-generated constructor stub
	}

	public String adicionarAtividadeDesejada(Atividade atividade){
		String deuCerto = "A atividade foi adicionada!";
		String deuErrado = "Já existe outro compromisso nesse hórario";
		if (verificarDisponibilidade(atividade) && evento.getAtividades().contains(atividade)){
			this.atividadesDesejadas.add(atividade);
			atividade.adicionarInscricao(this);
			return deuCerto;
		}
		return deuErrado;
	}

	public boolean verificarDisponibilidade(Atividade atividade) {
		for (Atividade at : atividadesDesejadas) {
			if (atividade.compararHorario(at)) return false;
		}
		return true;
	}
	public List<Atividade> getAtividadesDesejadas() {
		return atividadesDesejadas;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	
	

}
