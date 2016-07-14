package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CalcularConta {
	
	private Inscri��o inscri��o;
	private double total;
	private List<CupomPromocional> cupons;
	
	public CalcularConta(Inscri��o inscri��o) {
		this.inscri��o = inscri��o;
		this.cupons = new ArrayList<CupomPromocional>();
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


	public double getTotalBruto() {
		return total;
	}

	public Inscri��o getInscri��o() {
		return inscri��o;
	}

	public List<CupomPromocional> getCupons() {
		return Collections.unmodifiableList(cupons);
	}
	
	
	
	

}
