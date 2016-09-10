package br.edu.ifpi.eventos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifpi.eventos.modelo.usuario.Usuario;

@Entity
public class Responsabilidade {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Atividade atividade;
	@ManyToOne
	private Usuario responsavel;
	private String miniCurriculo;
	private boolean responsavelPrincipal;
	
	public Responsabilidade(Atividade atividade, Usuario responsavel) {
		this.atividade = atividade;
		this.responsavel = responsavel;
	}

	public void setResponsavelPrincipal(boolean responsavelPrincipal) {
		this.responsavelPrincipal = responsavelPrincipal;
	}
	
	public void setMiniCurriculo(String miniCurriculo) {
		this.miniCurriculo = miniCurriculo;
	}

}
