package br.com.trabalhojavaee.service;

/**
 * Excecoes para o service.
 * @author Paulo
 */
public class UsuarioInvalidoException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1071896537277884578L;

	public UsuarioInvalidoException() {
		super("Login ou senha invalidos!");
	}
}
