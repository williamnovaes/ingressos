package br.com.nx.tickets.rest;

public enum ECodigoRetorno {

	OK("1"),
	ERROR("2");
	
	private final String codigo;
	
	ECodigoRetorno(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
