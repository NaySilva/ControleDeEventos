package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CalcularConta {
	
	private Inscrição inscrição;
	private double total;
	private List<CupomPromocional> cupons;
	
	public CalcularConta(Inscrição inscrição) {
		this.inscrição = inscrição;
		this.cupons = new ArrayList<CupomPromocional>();
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


	public double getTotalBruto() {
		return total;
	}

	public Inscrição getInscrição() {
		return inscrição;
	}

	public List<CupomPromocional> getCupons() {
		return Collections.unmodifiableList(cupons);
	}
	
	
	
	

}
