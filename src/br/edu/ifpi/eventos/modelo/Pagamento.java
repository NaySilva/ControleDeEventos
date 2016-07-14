package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.eventos.util.Agenda;

public class Pagamento {
	
	private Inscri��o inscri��o;
	private List<CupomPromocional> cupons;
	private Agenda horarioPagamento;
	private boolean pago;
	
	public Pagamento(Inscri��o inscri��o) {
		this.inscri��o = inscri��o;
		this.cupons = new ArrayList<CupomPromocional>();
		this.pago = false;
	}
	
	public void pagarInscri��o(double valor){
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
		double totalBruto = inscri��o.getEvento().getPreco();
		for (Atividade at : inscri��o.getAtividadesDesejadas()) {
			totalBruto += at.getPreco();
		}
		return totalBruto;
	}
	
	public double totalDeDesconto(){
		double desconto = 0.0;
		for (CupomPromocional cupomPromocional : cupons) {
			desconto += cupomPromocional.valorDoDesconto(this.inscri��o);
		}
		return desconto;
	}
	
	public double calcularTotalComDesconto(){
		double totalComDesconto = calcularTotalBruto() - totalDeDesconto();
		return totalComDesconto;
	}

	public Inscri��o getInscri��o() {
		return inscri��o;
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
