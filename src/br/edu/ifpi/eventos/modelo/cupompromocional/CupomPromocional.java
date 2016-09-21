package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.modelo.agenda.Agenda;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;

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
		ativo = validade.depoisDoFim(Agenda.noMomento.getFim()) ? false : true;
	}
	
	public abstract BigDecimal valorDoDesconto(Inscricao inscricao);
	
	public boolean getAtivo() {
		return ativo;
	}
	
	

}