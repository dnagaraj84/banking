package com.revolut.banking.exception;

public class InSufficientMoneyException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7487615687336833379L;
	private final String accountId;
	
	public InSufficientMoneyException(String accountId)
	{
		this.accountId = accountId;
	}
	
	@Override
	public String getMessage()
	{
		return String.format("Insufficient money with accountId %s", accountId);
	}

}
