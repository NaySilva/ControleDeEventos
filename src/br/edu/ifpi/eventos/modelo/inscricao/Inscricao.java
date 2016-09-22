package br.edu.ifpi.eventos.modelo.inscricao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.ifpi.eventos.excecoes.AtividadeInexistenteNoEventoException;
import br.edu.ifpi.eventos.excecoes.AtividadeRepetidaException;
import br.edu.ifpi.eventos.excecoes.CupomInativoException;
import br.edu.ifpi.eventos.excecoes.InscricaoPagaException;
import br.edu.ifpi.eventos.modelo.cupompromocional.CupomPromocional;
import br.edu.ifpi.eventos.modelo.evento.Evento;
import br.edu.ifpi.eventos.modelo.item.Item;
import br.edu.ifpi.eventos.modelo.item.ItemUnico;
import br.edu.ifpi.eventos.modelo.pagamento.Pagamento;
import br.edu.ifpi.eventos.modelo.perfil.PerfilParticipante;
import br.edu.ifpi.eventos.notificacao.Subject;
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
	
	protected Inscricao() {}
	
	public Inscricao(Evento evento, PerfilParticipante perfil){
		this.evento = evento;
		this.perfil = perfil;
		this.pagamento = new Pagamento(this);
		this.carrinho = new ArrayList<Item>();
		addObserver(perfil);
		evento.adicionarInscricao(this);;
		perfil.adicionarInscricao(this);
	}

	public void adicionarItem(Item item) throws Exception {
			item.adicionarNoCarrinho(this);
	}
	
	public void adicionarUmItem(Item item){
		carrinho.add(item);
		setNotificacao("Novo Produto foi adicionado");
		notifyObservers();
	}
	
	public void retricoesDeAtividade(ItemUnico item) throws AtividadeRepetidaException, AtividadeInexistenteNoEventoException, InscricaoPagaException{
		if (carrinho.contains(item)) throw new AtividadeRepetidaException();
		if (!evento.getAtividades().contains(item.getAtividade())) throw new AtividadeInexistenteNoEventoException();
		if (pagamento.isPago()) throw new InscricaoPagaException();
	}
	
	public BigDecimal calcularTotalBruto(){
		BigDecimal totalBruto = new BigDecimal(0);
		for (Item p : getCarrinho()) {
			totalBruto = totalBruto.add(p.getPreco());
		}
		return totalBruto;
	}
	
	public BigDecimal calcularTotalComDesconto(){
		BigDecimal totalComDesconto = cupom!=null ? calcularTotalBruto().subtract(cupom.valorDoDesconto(this)) : calcularTotalBruto();
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
	
	public void setCupom(CupomPromocional cupom) throws Exception {
		if (cupom.getAtivo()){
			this.cupom = cupom;
		}else{
			throw new CupomInativoException();
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