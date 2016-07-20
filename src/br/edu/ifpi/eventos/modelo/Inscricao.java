package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.HorarioIndisponivelException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;

public class Inscricao {
	
	private Evento evento;
	private Perfil perfil;
	private List<Atividade> atividadesDesejadas = new ArrayList<Atividade>();
	private Pagamento pagamento;
	private List<CupomPromocional> cupons;
	
	public Inscricao(Evento evento, Perfil perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		this.cupons = new ArrayList<CupomPromocional>();
		evento.adicionarInscricao(this);;
		perfil.adicionarInscricao(this);
	}

	public void adicionarAtividadeDesejada(Atividade atividade) throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, HorarioIndisponivelException, InscricaoPagaException{
		if (atividadesDesejadas.contains(atividade)) throw new AtividadeRepetidaException();
		if (!evento.getAtividades().contains(atividade)) throw new AtividadeInexistenteNoEventoException();
		if (!verificarDisponibilidade(atividade)) throw new HorarioIndisponivelException();
		if (pagamento.isPago()) throw new InscricaoPagaException();
		atividadesDesejadas.add(atividade);
		atividade.adicionarInscricao(this);
	}

	public boolean verificarDisponibilidade(Atividade atividade) {
		for (Atividade at : atividadesDesejadas) {
			if (!atividade.getAgenda().compararHorario(at.getAgenda())) return false;
		}
		return true;
	}
	
	public double calcularTotalBruto(){
		double totalBruto = getEvento().getPreco();
		for (Atividade at : getAtividadesDesejadas()) {
			totalBruto += at.getPreco();
		}
		return totalBruto;
	}

	public void adicionarCupom(CupomPromocional cupom){
		if (cupom.getAtivo()) cupons.add(cupom);
	}

	public double totalDeDesconto(){
		double desconto = 0.0;
		for (CupomPromocional cupomPromocional : getCupons()) {
			desconto += cupomPromocional.valorDoDesconto(this);
		}
		return desconto;
	}
	
	public double calcularTotalComDesconto(){
		double totalComDesconto = calcularTotalBruto() - totalDeDesconto();
		return totalComDesconto;
	}
	
	public List<Atividade> getAtividadesDesejadas() {
		return Collections.unmodifiableList(atividadesDesejadas);
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public List<CupomPromocional> getCupons() {
		return Collections.unmodifiableList(cupons);
	}

	
	
	
	
	
	

}