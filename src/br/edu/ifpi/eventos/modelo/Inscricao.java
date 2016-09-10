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
import javax.persistence.Transient;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
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
	@ManyToMany(mappedBy="inscricoes")
	private List<Item> carrinho;
	@OneToOne
	private Pagamento pagamento;
	@OneToOne
	private CupomPromocional cupom;
	
	public Inscricao(Evento evento, PerfilParticipante perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		this.carrinho = new ArrayList<Item>();
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
	
	public double calcularTotalComDesconto(){
		double totalComDesconto = cupom!=null ? calcularTotalBruto() - cupom.valorDoDesconto(this) : calcularTotalBruto();
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

	public CupomPromocional getCupom() {
		return cupom;
	}
	
	public void setCupom(CupomPromocional cupom) {
		if (cupom.getAtivo()){
			this.cupom = cupom;
		}
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