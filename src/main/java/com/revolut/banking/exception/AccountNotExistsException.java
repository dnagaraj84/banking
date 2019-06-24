/**
 * 
 */
package com.revolut.banking.exception;

/**
 * @author Nags
 *
 */
public class AccountNotExistsException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9054849989728154505L;
	private final String accountId;
	
	public AccountNotExistsException(final String accountId)
	{
		this.accountId = accountId;
	}
	
	@Override
	public String getMessage()
	{
		return String.format("Account doen't exists with account ID %s", accountId);
	}

}
