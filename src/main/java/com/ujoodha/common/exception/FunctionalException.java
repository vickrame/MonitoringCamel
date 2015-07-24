/**
 * 
 */
package com.ujoodha.common.exception;

/**
 * @author vickrame
 *
 */
public class FunctionalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public FunctionalException(final String code, final String msg, Throwable th) {
		super(msg, th);
		this.code = code;

	}

}
