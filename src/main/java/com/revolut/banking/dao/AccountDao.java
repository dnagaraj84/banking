/**
 * 
 */
package com.revolut.banking.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.revolut.banking.exception.AccountAlreadyExistsException;
import com.revolut.banking.exception.AccountNotExistsException;
import com.revolut.banking.model.Account;

/**
 * 
 * @author Nags
 *
 */
public class AccountDao implements IAccountDao 
{
	public final static Map<String, Account> accountDataStore = new ConcurrentHashMap<>();
	

	@Override
	public Account create(final Account account) throws AccountAlreadyExistsException
	{
		if(accountDataStore.putIfAbsent(account.getAccountId(), account) != null)
		{
			throw new AccountAlreadyExistsException(account.getAccountId());
		}

		return account;
	}

	
	@Override
	public Account update(final String accountId, final Account account) throws AccountNotExistsException 
	{
		if(accountDataStore.putIfAbsent(accountId, account) == null)
		{
			throw new AccountNotExistsException(accountId);
		}
		
		final Account updatedAccount = new Account.Builder()
		        .setAccountNumber(accountId)
		        .setAccountHolder(account.getAccountHolder())
		        .setAccountBalance(account.getAccountBalance())
		        .build();

			accountDataStore.remove(accountId);
			accountDataStore.put(accountId, updatedAccount);
		
		return updatedAccount;
	}

	
	@Override
	public Account depositMoney(final Account account, final BigDecimal money) throws AccountNotExistsException
	{
		if(accountDataStore.get(account.getAccountId()) == null)
		{
			throw new AccountNotExistsException(account.getAccountId());
		}
		
		final BigDecimal afterWithdrawalAmount = account.getAccountBalance().add(money);
		
		final BigDecimal balanceAmountRounded = afterWithdrawalAmount.setScale(2, RoundingMode.HALF_EVEN);
		
		final Account updatedAccount = new Account.Builder()
		        .setAccountNumber(account.getAccountId())
		        .setAccountHolder(account.getAccountHolder())
		        .setAccountBalance(balanceAmountRounded)
		        .build();

		accountDataStore.put(account.getAccountId(), updatedAccount);
		
		return updatedAccount;
	}

	
	@Override
	public Account withdrawMoney(Account account, BigDecimal money) throws AccountNotExistsException 
	{
		if(accountDataStore.get(account.getAccountId()) == null)
		{
			throw new AccountNotExistsException(account.getAccountId());
		}
		
		final BigDecimal afterWithdrawalAmount = account.getAccountBalance().subtract(money);
		
		final BigDecimal balanceAmountRounded = afterWithdrawalAmount.setScale(2, RoundingMode.HALF_EVEN);
		
		final Account updatedAccount = new Account.Builder()
		        .setAccountNumber(account.getAccountId())
		        .setAccountHolder(account.getAccountHolder())
		        .setAccountBalance(balanceAmountRounded)
		        .build();

		accountDataStore.put(account.getAccountId(), updatedAccount);
		
		return updatedAccount;
	}

	
	@Override
	public List<Account> listAccounts()
	{
		return accountDataStore.values()
		           .stream()
		           .sorted((e1, e2) -> e1.getAccountId().compareTo(e2.getAccountId()))
		           .collect(Collectors.toList());			
	}
	

	@Override
	public Account findAccountById(String accountId) throws AccountNotExistsException 
	{
		if(accountDataStore.get(accountId) == null)
		{
			throw new AccountNotExistsException(accountId);
		}
		
		return accountDataStore.get(accountId);
	}
}
