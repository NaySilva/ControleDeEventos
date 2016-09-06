package br.edu.ifpi.eventos.modelo;

public class Responsavel {
	
	private Atividade atividade;
	private Usuario responsavel;
	private String miniCurriculo;
	private boolean responsavelPrincipal;
	
	public Responsavel(Atividade atividade, Usuario responsavel) {
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
