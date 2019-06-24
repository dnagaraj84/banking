package com.revolut.banking.exception;

public class InvalidMoneyTransferException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4815450173836242580L;
	
	
	@Override
	public String getMessage()
	{
		return String.format("Negative/Zero money cannot be transferred.");
	}


}
