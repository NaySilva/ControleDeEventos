package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ifpi.eventos.util.Agenda;

public abstract class CupomPromocional {
	
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
