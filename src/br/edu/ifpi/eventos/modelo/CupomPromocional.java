package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.util.Agenda;

@Entity
public abstract class CupomPromocional {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Agenda validade;
	private boolean ativo;
	
	public CupomPromocional(Agenda validade) {
		this.validade = validade;
		verificarAValidade();
	}
	
	public void verificarAValidade(){
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		ativo = validade.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim()) ? false : true;
	}
	
	public abstract double valorDoDesconto(Inscricao insc);
	
	public boolean getAtivo() {
		return ativo;
	}
	
	

}
