package br.edu.ifpi.eventos.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

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
import br.edu.ifpi.eventos.util.Produto;
import br.edu.ifpi.eventos.util.Subject;
import br.edu.ifpi.eventos.util.TipoDeParticipacao;
@Entity
public class Inscricao extends Subject{
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private PerfilParticipante perfil;
	@ManyToOne
	private Evento evento;
	@ManyToMany(mappedBy="inscricao")
	private List<Item> carrinho;
	@OneToOne
	private Pagamento pagamento;
	@OneToMany(mappedBy="inscricao")
	private List<CupomPromocional> cupons;
	
	public Inscricao(Evento evento, PerfilParticipante perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		this.carrinho = new ArrayList<Item>();
		this.cupons = new ArrayList<CupomPromocional>();
		addObserver(perfil);
		evento.adicionarInscricao(this);;
		perfil.adicionarInscricao(this, TipoDeParticipacao.Estudante);
	}

	public void adicionarItem(Item item) throws Exception {
			item.adicionarNoCarrinho(this);
			setNotificacao("Novo Produto foi adicionado");
			notifyObservers();
	}
	
	public void adicionarUmItem(Item item){
		carrinho.add(item);
	}
	
	public void retricoesDeAtividade(ItemUnico item) throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, InscricaoPagaException{
		if (carrinho.contains(item)) throw new AtividadeRepetidaException();
		if (!evento.getAtividades().contains(item.getAtividade())) throw new AtividadeInexistenteNoEventoException();
		if (pagamento.isPago()) throw new InscricaoPagaException();
	}
	
	public double calcularTotalBruto(){
		double totalBruto = 0;
		for (Item p : getCarrinho()) {
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
	
	public List<Item> getCarrinho() {
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

	public PerfilParticipante getPerfil() {
		return perfil;
	}
	

	@Override
	public void setNotificacao(String mensagem) {
		notificacao = "Nova notificação da sua inscricao:\n";
		notificacao += mensagem;
		
	}

	
	
	
	
	

}