package br.edu.ifpi.eventos.modelo.perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.edu.ifpi.eventos.modelo.inscricao.Inscricao;
import br.edu.ifpi.eventos.modelo.usuario.Usuario;
import br.edu.ifpi.eventos.util.Observer;
import sun.security.provider.certpath.OCSP.RevocationStatus;
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
