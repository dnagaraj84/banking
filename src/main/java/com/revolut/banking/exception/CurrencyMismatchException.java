/**
 * 
 */
package com.revolut.banking.exception;

/**
 * @author Nags
 *
 */
public class CurrencyMismatchException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 397087983696817644L;
	private final String fromAccountNo;
	private final String toAccountNo;
	
	public CurrencyMismatchException(String fromAccountNo, String toAccountNo)
	{
		this.fromAccountNo = fromAccountNo;
		this.toAccountNo = toAccountNo;
	}
	
	@Override
	public String getMessage()
	{
		return String.format("Currency mismatch tranfer between %s and %s", fromAccountNo, toAccountNo);
	}

}
