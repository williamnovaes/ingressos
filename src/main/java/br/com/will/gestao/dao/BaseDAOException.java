package br.com.will.gestao.dao;

public class BaseDAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseDAOException(String mensagem) {
		super(mensagem);
	}
}