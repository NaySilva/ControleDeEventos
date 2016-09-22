package br.edu.ifpi.eventos.modelo.cupompromocional;

import java.math.BigDecimal;

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
	protected String descricao;
	@OneToOne
	private Agenda validade;
	protected BigDecimal porcentagem;
	private boolean ativo;
	
	protected CupomPromocional(){}
	
	public CupomPromocional(String descricao, Agenda validade, BigDecimal porcentagem) {
		this.descricao = descricao;
		this.validade = validade;
		this.porcentagem = porcentagem;
		verificarAValidade();
	}
	
	public void verificarAValidade(){
		ativo = validade.antes(Agenda.noMomento.getFim()) ? false : true;
	}
	
	public abstract BigDecimal valorDoDesconto(Inscricao inscricao);
	
	public boolean getAtivo() {
		return ativo;
	}
	
	

}
