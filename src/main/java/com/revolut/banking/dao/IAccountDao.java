package com.revolut.banking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.revolut.banking.exception.AccountAlreadyExistsException;
import com.revolut.banking.exception.AccountNotExistsException;
import com.revolut.banking.model.Account;

public interface IAccountDao 
{
	/**
	 * Create {@code Account} entity based on the data.
	 * 
	 * @param account The {@code Account} The account which needs to be created.
	 * 
	 * @return The {@code Account} which been created.
	 * 
	 * @throws AccountAlreadyExistsException The AccountAlreadyExistsException exception thrown when account already exists with same account ID.
	 */
	Account create(Account account) throws AccountAlreadyExistsException;
	
	
	/**
	 * Update {@code Account} entity based on the data.
	 * 
	 * @param account The {@code Account} The account which needs to be updated.
	 * 
	 * @return The {@code Account} which been updated.
	 * 
	 * @throws AccountAlreadyExistsException The AccountNotExistsException exception thrown when account doesn't exists.
	 */
	Account update(String accountNo, Account account) throws AccountNotExistsException;
	
	
	/**
	 * Finds the account associated with account ID.
	 * 
	 * @param accountId The account ID. Will never be {@code null}.
	 * 
	 * @return The {@code Account} which been updated.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException exception thrown when account doesn't exists.
	 */
	Account findAccountById(String accountId) throws AccountNotExistsException;
	
	
	/**
	 * Deposit amount into the account associated with account ID.
	 * 
	 * @param accountId The account ID. Will never be {@code null}.
	 * 
	 * @return The {@code Account} which been credited.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException exception thrown when account doesn't exists.
	 */
	Account depositMoney(Account account, BigDecimal money) throws AccountNotExistsException;
	
	
	/**
	 * Withdraw amount into the account associated with account ID.
	 * 
	 * @param accountId The account ID. Will never be {@code null}.
	 * 
	 * @return The {@code Account} which been withdrawn.
	 * 
	 * @throws AccountNotExistsException The AccountNotExistsException exception thrown when account doesn't exists.
	 */
	Account withdrawMoney(Account account, BigDecimal money) throws AccountNotExistsException;
	
	
	/**
	 * Returns all the accounts.
	 * 
	 * @return The list of all accounts.
	 */
	List<Account> listAccounts();
}
