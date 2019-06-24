package com.revolut.banking.exception;

public class SameAccountTransferException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -671101350821056989L;

	@Override
	public String getMessage()
	{
		return String.format("Same account transfer is invalid");
	}

}
