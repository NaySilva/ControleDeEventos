package br.edu.ifpi.eventos.modelo;


import java.util.ArrayList;
import java.util.List;

public class Inscricao {
	
	private Evento evento;
	private Usuario usuario;
	private List<Atividade> atividadesDesejadas = new ArrayList<Atividade>();
	private boolean pago;
	
	public Inscricao(Evento evento, Usuario usuario) {
		this.evento = evento;
		this.usuario = usuario;
	}

	public String adicionarAtividadeDesejada(Atividade atividade){
		String deuCerto = "A atividade foi adicionada!";
		String deuErrado = "Já existe outro compromisso nesse hórario";
		if (verificarDisponibilidade(atividade)){
			this.atividadesDesejadas.add(atividade);
			return deuCerto;
		}
		return deuErrado;
	}

	private boolean verificarDisponibilidade(Atividade atividade) {
		for (Atividade at : atividadesDesejadas) {
			if (atividade.compararHorario(at)) return false;
		}
		return true;
	}
	

}
