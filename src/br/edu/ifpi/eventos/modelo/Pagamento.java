package br.edu.ifpi.eventos.modelo;


import br.edu.ifpi.eventos.util.Agenda;

public class Pagamento {
	
	Inscricao inscricao;
	private Agenda horarioPagamento;
	private boolean pago;
	
	public Pagamento(Inscricao inscricao) {
		this.inscricao = inscricao;
		this.pago = false;
	}
	
	public void pagarInscricao(double valor){
		double total = inscricao.calcularTotalComDesconto();
		if (total == valor) {
			pago = true;
			horarioPagamento = Agenda.noMomento;
		}else pago = false;		
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public Agenda getHorarioPagamento() {
		return horarioPagamento;
	}

	public boolean isPago() {
		return pago;
	}
	
	
	
	
	
	

}
