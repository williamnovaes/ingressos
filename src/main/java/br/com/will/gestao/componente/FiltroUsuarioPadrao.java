package br.com.will.gestao.componente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.will.gestao.entidade.Usuario;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FiltroUsuarioPadrao extends FiltroPermissao {

	private static final long serialVersionUID = 1L;
	
	public FiltroUsuarioPadrao(Usuario usuario) {
		super(usuario);
	}
}
