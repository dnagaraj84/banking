package com.revolut.banking.exception;

public class AccountAlreadyExistsException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041567857806259294L;
	
	private final String accountId;
	
	public AccountAlreadyExistsException(String accountId)
	{
		this.accountId = accountId;
	}
	
	@Override
	public String getMessage()
	{
		return String.format("Account already exists with accountId %s", accountId);
	}
}
