package com.revolut.banking.dao;

import java.math.BigDecimal;

public interface ITransactionDao 
{
	void transferFunds(String accountFrom, String accountTo, BigDecimal amount) throws Exception; 
}
