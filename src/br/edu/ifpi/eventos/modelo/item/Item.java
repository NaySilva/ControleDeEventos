package br.edu.ifpi.eventos.modelo.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.util.Subject;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "item_class_mae")
public abstract class Item extends Subject{
	
	@Id
	@GeneratedValue
	protected long id;
	protected BigDecimal preco;
	@ManyToMany
	private List<Inscricao> inscricoes;
	
	public Item(BigDecimal preco) {
		this.preco = preco;
		this.inscricoes = new ArrayList<Inscricao>();
	}

	public abstract void adicionarNoCarrinho(Inscricao inscricao) throws Exception;
	
	public void adicionarInscricao(Inscricao inscricao){
		inscricoes.add(inscricao);
		addObserver(inscricao.getPerfil());
		limparNotificacao();
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
		setNotificacao("Novo preço: " + preco);
		notifyObservers();
	}

	@Override
	public void setNotificacao(String mensagem) {
		notificacao = "Nova notificação do item " + "/n";
		notificacao += mensagem;
	}
	
	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}
	
	

}
