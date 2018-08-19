package br.com.luciano.npj.service.exception;

public class LoginUsuarioJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LoginUsuarioJaExistenteException(String message) {
		super(message);
	}

}
