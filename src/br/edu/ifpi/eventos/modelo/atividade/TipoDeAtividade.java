package br.edu.ifpi.eventos.modelo.atividade;

public enum TipoDeAtividade {
	
	Palestra("Palestra"), Minicurso("Minicurso"), 
	Tutorial("Tutorial"), MesaRedonda("Mesa Redonda"), 
	CoffeBreak("Coffe Break"), Workshop("Workshop"), Outro("Outro");
	
	private String descricao;
	
	private TipoDeAtividade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDeAtividade porValor(String valor){
		if (valor.equals("Palestra")) return Palestra;
		else if (valor.equals("Minicurso")) return Minicurso;
		else if (valor.equals("Tutorial")) return Tutorial;
		else if (valor.equals("Mesa Redonda")) return MesaRedonda;
		else if (valor.equals("Coffe Break")) return CoffeBreak;
		else if (valor.equals("Workshop")) return CoffeBreak;
		else return Outro;
	}

}
