package br.edu.ifpi.eventos.modelo;

import br.edu.ifpi.eventos.util.Agenda;

public class Minicurso extends Atividade {
	
	private boolean temCertificado;

	public Minicurso(String nome, Agenda comeco, Agenda fim) {
		super(nome, comeco, fim);
		this.temCertificado = false;
	}

	public boolean isTemCertificado() {
		return temCertificado;
	}

	public void setTemCertificado(boolean temCertificado) {
		this.temCertificado = temCertificado;
	}
	
	




}
