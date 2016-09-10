package br.edu.ifpi.eventos.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(mappedBy="pagamento")
	private Inscricao inscricao;
	@OneToOne
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
