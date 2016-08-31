package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
@Entity
public class Inscricao {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Perfil perfil;
	@ManyToOne
	private Evento evento;
	@ManyToMany(mappedBy="inscricoes")
	private List<Produto> carrinho;
	@OneToOne
	private Pagamento pagamento;
	@OneToMany
	private List<CupomPromocional> cupons;
	
	public Inscricao(Evento evento, Perfil perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		this.carrinho = new ArrayList<Produto>();
		this.cupons = new ArrayList<CupomPromocional>();
		evento.adicionarInscricao(this);;
		perfil.adicionarInscricao(this);
	}

	public void adicionarProduto(Produto produto) {
		produto.adicionarNoCarrinho(this);
	}
	
	
	public void adicionarTodasAsAtividades(List<Atividade> atividades){
		carrinho.addAll(atividades);
	}
	
	public void retricoesDeAtividade(Atividade atividade) throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, InscricaoPagaException{
		if (carrinho.contains(atividade)) throw new AtividadeRepetidaException();
		if (!evento.getAtividades().contains(atividade)) throw new AtividadeInexistenteNoEventoException();
		if (pagamento.isPago()) throw new InscricaoPagaException();
	}
	
	public double calcularTotalBruto(){
		double totalBruto = 0;
		for (Produto p : getCarrinho()) {
			totalBruto += p.getPreco();
		}
		return totalBruto;
	}

	public void adicionarCupom(CupomPromocional cupom){
		if (cupom.getAtivo()) cupons.add(cupom);
	}

	public double totalDeDesconto(){
		double desconto = 0.0;
		for (CupomPromocional cupomPromocional : getCupons()) {
			desconto += cupomPromocional.valorDoDesconto(this);
		}
		return desconto;
	}
	
	public double calcularTotalComDesconto(){
		double totalComDesconto = calcularTotalBruto() - totalDeDesconto();
		return totalComDesconto;
	}
	
	public List<Produto> getCarrinho() {
		return Collections.unmodifiableList(carrinho);
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public List<CupomPromocional> getCupons() {
		return Collections.unmodifiableList(cupons);
	}

	
	
	
	
	
	

}