package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifpi.eventos.modelo.atividade.Atividade;
import br.edu.ifpi.eventos.modelo.atividade.AtividadeBuilder;
import br.edu.ifpi.eventos.modelo.responsabilidade.Responsabilidade;
import br.edu.ifpi.eventos.modelo.usuario.Pessoa;

public class ResponsabilidadeTeste {

	@Test
	public void Adicionar_Minicurriculo_Para_Responsavel() {
		Responsabilidade res = new Responsabilidade(new AtividadeBuilder().getAtividade(), new Pessoa("Maria"));
		String texto = "Graduação em Analise e Desenvolvimento de Sistema";
		res.setMiniCurriculo(texto);
		assertEquals(texto, res.getMiniCurriculo());
	}
	
	@Test
	public void Considerar_Pessoa_Como_Responsavel_Principal_Da_Atividade(){
		Pessoa pessoa = new Pessoa("Maria");
		Responsabilidade res = new Responsabilidade(new AtividadeBuilder().getAtividade(), pessoa);
		res.setResponsavelPrincipal(true);
		assertEquals(pessoa, res.getResponsavel());
	}

}
