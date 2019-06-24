package com.revolut.banking.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.log4j.Logger;

import com.revolut.banking.dao.AccountDao;
import com.revolut.banking.dao.IAccountDao;
import com.revolut.banking.exception.AccountAlreadyExistsException;
import com.revolut.banking.exception.AccountNotExistsException;
import com.revolut.banking.exception.InSufficientMoneyException;
import com.revolut.banking.exception.InvalidMoneyTransferException;
import com.revolut.banking.model.Account;
import com.revolut.banking.model.Transaction;

public class AccountService implements IAccountService 
{
	private static Logger log = Logger.getLogger(AccountService.class);
	
	private final IAccountDao accountDao = new AccountDao();

	@Override
	public List<Account> getAllAccounts() 
	{
		log.info("In getAllAccounts - Fetch all accounts");
		return accountDao.listAccounts();
	}

	@Override
	public Account findAccountById(String accountId) throws AccountNotExistsException 
	{
		return accountDao.findAccountById(accountId);
	}

	@Override
	public BigDecimal getAccountBalance(String accountId) throws AccountNotExistsException
	{
		final Account account =  accountDao.findAccountById(accountId);
		
		return account.getAccountBalance();
	}

	@Override
	public Account createAccount(Account account) throws AccountAlreadyExistsException 
	{
		Account createdAccount = accountDao.create(account);
		
		return createdAccount;
		
	}
	

	@Override
	public synchronized void transferFunds(final Transaction transaction) throws AccountNotExistsException, InvalidMoneyTransferException, InSufficientMoneyException
	{
		final Account fromAccount = accountDao.findAccountById(transaction.getFromAccountId());
		final Account toAccount = accountDao.findAccountById(transaction.getToAccountId());	
		
		if(transaction.getAmount().compareTo(BigDecimal.ZERO) == -1)
		{
			throw new InvalidMoneyTransferException();
		}
		
		if(fromAccount.getAccountBalance().compareTo(transaction.getAmount()) != 1)
		{
			throw new InSufficientMoneyException(fromAccount.getAccountId());
		}
		
		final BigDecimal amountRounded = transaction.getAmount().setScale(2, RoundingMode.HALF_EVEN);	
		
		accountDao.withdrawMoney(fromAccount, amountRounded);
		
		accountDao.depositMoney(toAccount, amountRounded);		
	}

}
