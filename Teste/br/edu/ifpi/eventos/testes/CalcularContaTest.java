package br.edu.ifpi.eventos.testes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpi.eventos.cupom.Lote_I;
import br.edu.ifpi.eventos.cupom.Palestras_50;
import br.edu.ifpi.eventos.modelo.Agenda;
import br.edu.ifpi.eventos.modelo.Atividade;
import br.edu.ifpi.eventos.modelo.CalcularConta;
import br.edu.ifpi.eventos.modelo.CupomPromocional;
import br.edu.ifpi.eventos.modelo.Evento;
import br.edu.ifpi.eventos.modelo.Inscrição;
import br.edu.ifpi.eventos.modelo.Minicurso;
import br.edu.ifpi.eventos.modelo.Palestra;
import br.edu.ifpi.eventos.modelo.PerfilDeUsuario;
import br.edu.ifpi.eventos.modelo.Semana;
import br.edu.ifpi.eventos.modelo.Usuario;
import br.edu.ifpi.eventos.util.DataEHora;

public class CalcularContaTest {
	
	DataEHora dh1, dh2, dh3, dh4, dh5, dh6;
	Agenda ag1, ag2, ag3, ag4;
	Evento sem;
	Usuario usu;
	Inscrição insc;
	Atividade mc, p, p2;
	CalcularConta cc;
	
	@Before
	public void inicialização(){
		dh1 = new DataEHora(LocalDate.of(2016, 7, 11), LocalTime.of(8, 00));
		dh2 = new DataEHora(LocalDate.of(2016, 7, 15), LocalTime.of(18, 00));
		dh3 = new DataEHora(LocalDate.of(2016, 7, 11), LocalTime.of(12, 00));
		dh4 = new DataEHora(LocalDate.of(2016, 7, 13), LocalTime.of(14, 00));
		dh5 = new DataEHora(LocalDate.of(2016, 7, 13), LocalTime.of(16, 00));
		dh6 = new DataEHora(LocalDate.of(2016, 7, 13), LocalTime.of(18, 00));
		ag1 = new Agenda(dh1, dh2);
		ag2 = new Agenda(dh1, dh3);
		ag3 = new Agenda(dh4, dh5);
		ag4 = new Agenda(dh4, dh6);
		sem = new Semana("Semana de Quimica", ag1);
		usu = new Usuario("Maria",new PerfilDeUsuario("Participante"));
		insc = new Inscrição(sem, usu);
		mc = new Minicurso("Analitica", ag2);
		p = new Palestra("GMRV", ag3);
		p2 = new Palestra("Nanometria", ag4);
		cc = new CalcularConta(insc);
		sem.setPreco(60.00);
		mc.setPreco(50.00);
		p.setPreco(80);
		p2.setPreco(40);
	}

	@Test
	public void Verificar_O_Total_Bruto_Sem_Entra_P2_E_Mc_Pelas_Retricoes() {
		sem.adicionarAtividade(p);
		sem.adicionarAtividade(p2);
		insc.adicionarAtividadeDesejada(mc);
		insc.adicionarAtividadeDesejada(p);
		insc.adicionarAtividadeDesejada(p2);
		assertEquals(140, cc.calcularTotalBruto(), 0.00001);
	}
	
	@Test
	public void Adicionar_Cupom_Palestras_50_E_Verificar_Desconto(){
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(p);
		CupomPromocional cp = new Palestras_50();
		cc.adicionarCupom(cp);
		assertEquals(40.0,cp.valorDoDesconto(cc.getInscrição()), 0.00001);
	}
	
	@Test
	public void Verificar_Total_Desconto_Depois_De_Adicionar_Cupons(){
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(p);
		cc.adicionarCupom(new Palestras_50());
		cc.adicionarCupom(new Lote_I());
		assertEquals(70, cc.totalDeDesconto(), 0.00001);
	}
	
	@Test
	public void Verificar_Valor_Total_Com_Desconto_De_Uma_Inscricao_Completa_E_Com_Cupons(){
		sem.adicionarAtividade(mc);
		sem.adicionarAtividade(p);
		insc.adicionarAtividadeDesejada(mc);
		insc.adicionarAtividadeDesejada(p);
		cc.adicionarCupom(new Palestras_50());
		cc.adicionarCupom(new Lote_I());
		assertEquals(120, cc.calcularTotalComDesconto(), 0.00001);
	}

}
