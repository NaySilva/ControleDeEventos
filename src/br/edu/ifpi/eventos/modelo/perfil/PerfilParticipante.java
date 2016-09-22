package br.edu.ifpi.eventos.modelo.perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.edu.ifpi.eventos.enums.TipoDeParticipacao;
import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;

@Entity
@DiscriminatorValue("perfil-participante")
public final class PerfilParticipante extends Perfil {
	
	@OneToMany(mappedBy="perfil")
	private List<Inscricao> inscricoes;
	private TipoDeParticipacao tipo;
	
	PerfilParticipante() {}
	
	public PerfilParticipante(Usuario usuario) {
		super(usuario);
		this.inscricoes = new ArrayList<Inscricao>();
	}
	
	public PerfilParticipante comTipoDeParticipacao(TipoDeParticipacao participacao){
		this.tipo = participacao;
		return this;
	}
	
	public void adicionarInscricao(Inscricao inscricao){
		this.inscricoes.add(inscricao);
	}

	public List<Inscricao> getInscricoes() {
		return Collections.unmodifiableList(inscricoes);
	}

	public void setTipo(TipoDeParticipacao tipo) {
		this.tipo = tipo;
	}
	
	public TipoDeParticipacao getTipo() {
		return tipo;
	}


}
