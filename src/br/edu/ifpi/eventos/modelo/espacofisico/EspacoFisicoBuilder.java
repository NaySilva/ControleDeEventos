package br.edu.ifpi.eventos.modelo.espacofisico;

import br.edu.ifpi.eventos.enums.TipoEspacoFisico;
import br.edu.ifpi.eventos.modelo.atividade.Atividade;

public class EspacoFisicoBuilder {
	
	private EspacoFisico espacoFisico;
	
	public EspacoFisicoBuilder() {
		this.espacoFisico = new EspacoFisico();
	}
	
	public EspacoFisicoBuilder comDescricao(String descricao){
		this.espacoFisico.descricao = descricao;
		return this;
	}
	
	public EspacoFisicoBuilder doTipo(TipoEspacoFisico tipo){
		this.espacoFisico.tipo = tipo;
		return this;
	}
	
	public EspacoFisicoBuilder comCapacidadeDe(int capacidade){
		this.espacoFisico.capacidade = capacidade;
		return this;
	}
	
	public EspacoFisicoBuilder noEndereco(String endereco){
		this.espacoFisico.endereco = endereco;
		return this;
	}

	public EspacoFisicoBuilder comLocalExterno(EspacoFisico local){
		this.espacoFisico.localExterno = local;
		local.adicionarLocalInterno(this.espacoFisico);
		return this;
	}
	
	public EspacoFisicoBuilder paraAtividade(Atividade atividade){
		this.espacoFisico.atividade = atividade;
		return this;
	}
	
	public EspacoFisico getEspacoFisico() {
		return espacoFisico;
	}

}
