package com.revolut.banking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

import com.revolut.banking.model.Account;
import com.revolut.banking.model.Transaction;
import com.revolut.banking.model.User;
import com.revolut.banking.service.AccountService;

public class AccountServiceTest {

	private AccountService accountService = new AccountService();
	private int threadCount = 100;
	private BigDecimal amount = new BigDecimal(1);

	@Test
	public void testUpdateBalance() throws Exception
	{
		final Account account1 = new Account("XXXXX", new User("BRAIN", "LARA", "BRAIN.LARA@abc.com"), new BigDecimal(1000));
		final Account account2 = new Account("YYYYY", new User("NICOLAS", "CAGE", "NICOLAS.CAGE@abc.com"),new BigDecimal(1000));

		this.accountService.createAccount(account1);
		this.accountService.createAccount(account2);
		
		final List<Account> accounts = accountService.getAllAccounts();

		Assert.assertEquals(2, accounts.size());

		Transaction transactionOne = new Transaction(amount, "XXXXX", "YYYYY");

		Assert.assertEquals("The balance is 1000", 1000, account1.getAccountBalance().intValue());

		final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

		final List<Future<Void>> futures = new ArrayList<Future<Void>>();
		
		for (int x = 0; x < threadCount; x++)
		{
			Callable<Void> callable = new Callable<Void>()
			{
				@Override
				public Void call() throws Exception 
				{
					accountService.transferFunds(transactionOne);
					return null;
				}
			};
			Future<Void> submit = executorService.submit(callable);
			futures.add(submit);
		}

		final List<Exception> exceptions = new ArrayList<Exception>();
		
		for (Future<Void> future : futures)
		{
			try 
			{
				future.get();
			} 
			catch (Exception e) 
			{
				exceptions.add(e);
				
				e.printStackTrace(System.err);
			}
		}

		executorService.shutdown();

		final BigDecimal sourceBalance = accountService.getAccountBalance("YYYYY");		
		final BigDecimal destinationBalance = accountService.getAccountBalance("XXXXX");
		
		Assert.assertEquals(1100, sourceBalance.intValue());
		Assert.assertEquals(900, destinationBalance.intValue());
	}
}
