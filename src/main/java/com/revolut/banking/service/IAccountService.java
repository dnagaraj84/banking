package com.revolut.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.revolut.banking.exception.AccountAlreadyExistsException;
import com.revolut.banking.exception.AccountNotExistsException;
import com.revolut.banking.exception.InSufficientMoneyException;
import com.revolut.banking.exception.InvalidMoneyTransferException;
import com.revolut.banking.model.Account;
import com.revolut.banking.model.Transaction;

public interface IAccountService 
{
	/**
	 * Method returns all the accounts.
	 * 
	 * @return The list of {@code Account}. Will never return {@code null}.
	 */
	List<Account> getAllAccounts();
	
	
	/**
	 * Finds the {@code Account} based on account ID.
	 * 
	 * @param accountId The account ID. Will never be {@code null}.
	 * 
	 * @return The {@code Account}. May return {@code null}.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException is thrown when account doesn't exists with ID.
	 */
	Account findAccountById(String accountId) throws AccountNotExistsException;
	
	
	/**
	 * Find account balance based on account ID.
	 * 
	 * @param accountId The account ID. Will never be {@code null}.
	 * 
	 * @return The balance amount. May return {@code null}.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException is thrown when account doesn't exists with ID.
	 */
	BigDecimal getAccountBalance(String accountId) throws AccountNotExistsException;
	
	
	/**
	 * Create entity {@code Account} based on info.
	 * 
	 * @param account The entity {@code Account} to be created.
	 * 
	 * @return The {@code Account} which been created.
	 * 
	 * @throws AccountAlreadyExistsException The exception is thrown when account with ID already exists.
	 */
	Account createAccount(Account account) throws AccountAlreadyExistsException;
	
	
	/**
	 * Transfer amount from one account to another account.
	 * 
	 * @param transaction The entity {@code Transaction}. Will never be {@code null}.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException exception thrown when account don't exists.	
	 * @throws InvalidMoneyTransferException The InvalidMoneyTransferException exception thrown when invalid money been transferred.
	 * @throws InSufficientMoneyException The InSufficientMoneyException exception is thrown when there is insufficent money in from account.
	 */
	void transferFunds(Transaction transaction) throws AccountNotExistsException, InvalidMoneyTransferException, InSufficientMoneyException;
}
