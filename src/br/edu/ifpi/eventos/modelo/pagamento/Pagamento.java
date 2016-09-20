package br.edu.ifpi.eventos.modelo.pagamento;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
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
	
	public void pagarInscricao(BigDecimal valor){
		System.out.println("OIIIIIIIII\n"+valor.toString());
		BigDecimal total = inscricao.calcularTotalComDesconto();
		System.out.println(inscricao.calcularTotalComDesconto().toString());
		if (total.equals(valor)) {
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
