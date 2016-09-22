package br.edu.ifpi.eventos.modelo.responsabilidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.pessoa.Pessoa;

@Entity
public class Responsabilidade {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Atividade atividade;
	@ManyToOne
	private Pessoa responsavel;
	private String miniCurriculo;
	private boolean responsavelPrincipal;
	
	protected Responsabilidade(){}
	
	public Responsabilidade(Atividade atividade, Pessoa responsavel) {
		this.atividade = atividade;
		atividade.adicionarResponsavel(this); 
		this.responsavel = responsavel;
	}

	public void setResponsavelPrincipal(boolean responsavelPrincipal) {
		this.responsavelPrincipal = responsavelPrincipal;
	}
	
	public void setMiniCurriculo(String miniCurriculo) {
		this.miniCurriculo = miniCurriculo;
	}

	public String getMiniCurriculo() {
		return miniCurriculo;
	}
	
	public boolean isResponsavelPrincipal() {
		return responsavelPrincipal;
	}
	
	public Pessoa getResponsavel() {
		return responsavel;
	}
}
