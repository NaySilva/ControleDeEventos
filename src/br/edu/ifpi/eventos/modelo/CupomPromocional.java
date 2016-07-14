package br.edu.ifpi.eventos.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ifpi.eventos.util.Agenda;
import br.edu.ifpi.eventos.util.TipoDeCupom;

public abstract class CupomPromocional {
	
	private Agenda validade;
	private TipoDeCupom tipo; 
	private boolean ativo;
	
	public CupomPromocional(Agenda validade, TipoDeCupom tipo) {
		this.validade = validade;
		this.tipo = tipo;
		verificarAValidade();
	}
	
	public void verificarAValidade(){
		Agenda hoje = new Agenda(LocalDate.now(), LocalTime.now());
		ativo = validade.depoisDoFim(hoje.getDiaFim(), hoje.getHoraFim()) ? false : true;
	}
	
	public abstract double valorDoDesconto(Inscrição insc);
	
	public boolean getAtivo() {
		return ativo;
	}
	
	

}
