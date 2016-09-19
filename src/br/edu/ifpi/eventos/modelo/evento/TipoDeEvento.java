package br.edu.ifpi.eventos.modelo.evento;

public enum TipoDeEvento {
	
	Semana("Semana"), Simposio("Simposio"), Congresso("Congresso"), Outro("Outro");
	
	private String descricao;
	
	private TipoDeEvento(String str) {
		descricao = str;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDeEvento porValor(String value){
		if (value.equals("Semana")) return Semana;
		if (value.equals("Simposio")) return Simposio;
		if (value.equals("Congresso")) return Congresso;
		else return Outro;
	}

}
