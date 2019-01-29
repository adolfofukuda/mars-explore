package br.elo7.mars.domain.exceptions;

public class InvalidCoordinateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2433744409111928906L;

	public InvalidCoordinateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidCoordinateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidCoordinateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCoordinateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCoordinateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
