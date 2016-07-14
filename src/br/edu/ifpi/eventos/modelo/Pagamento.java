package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;

public class Pagamento {
	
	private Inscrição inscrição;
	private List<CupomPromocional> cupons;
	private Agenda horarioPagamento;
	private boolean pago;
	
	public Pagamento(Inscrição inscrição) {
		this.inscrição = inscrição;
		this.cupons = new ArrayList<CupomPromocional>();
		this.pago = false;
	}
	
	public void pagarInscrição(double valor){
		double total = calcularTotalComDesconto();
		if (total == valor) {
			pago = true;
			horarioPagamento = Agenda.hoje;
		}else pago = false;		
	}

	public void adicionarCupom(CupomPromocional cupom){
		if (cupom.getAtivo()) cupons.add(cupom);
	}

	public double calcularTotalBruto(){
		double totalBruto = inscrição.getEvento().getPreco();
		for (Atividade at : inscrição.getAtividadesDesejadas()) {
			totalBruto += at.getPreco();
		}
		return totalBruto;
	}
	
	public double totalDeDesconto(){
		double desconto = 0.0;
		for (CupomPromocional cupomPromocional : cupons) {
			desconto += cupomPromocional.valorDoDesconto(this.inscrição);
		}
		return desconto;
	}
	
	public double calcularTotalComDesconto(){
		double totalComDesconto = calcularTotalBruto() - totalDeDesconto();
		return totalComDesconto;
	}

	public Inscrição getInscrição() {
		return inscrição;
	}

	public List<CupomPromocional> getCupons() {
		return cupons;
	}

	public Agenda getHorarioPagamento() {
		return horarioPagamento;
	}

	public void setHorarioPagamento(Agenda agenda) {
		this.horarioPagamento = agenda;
	}

	public boolean isPago() {
		return pago;
	}
	
	
	
	
	
	

}
