package br.com.poli.Exception;

public class GenericException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericException() {
		super("Valor duplicado");
	}
}
