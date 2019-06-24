/**
 * 
 */
package com.revolut.banking.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.revolut.banking.exception.AccountAlreadyExistsException;
import com.revolut.banking.exception.AccountNotExistsException;
import com.revolut.banking.exception.InSufficientMoneyException;
import com.revolut.banking.model.Account;
import com.revolut.banking.model.Transaction;
import com.revolut.banking.service.AccountService;
import com.revolut.banking.service.IAccountService;

/**
 * The Controller class for transfer web services.
 * 
 * @author Nags
 */
@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController 
{
	private static Logger log = Logger.getLogger(AccountController.class);
	
	private final IAccountService accountService = new AccountService();
	
	@GET
	@Path("/getAllAccounts")
	public List<Account> getAllAccounts()
	{
		log.info("Fetch all the accounts");
		
		return accountService.getAllAccounts();
	}
	
	
	@GET
	@Path("/{accountId}")
	public Account getAccountById(@PathParam("accountId") final String accountId)
	{
		log.info("Fetch account details of:"+accountId);
		
		try
		{
			return accountService.findAccountById(accountId);
		}
		catch(AccountNotExistsException accountNotExistsException)
		{
			final Response response = Response.status(Response.Status.CONFLICT).entity(accountNotExistsException.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
		catch(Exception exception)
		{
			final Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
	}
	
	
	@GET
	@Path("/{accountId}/getBalance")
	public BigDecimal getBalance(@PathParam("accountId") final String accountId)
	{
		log.info("Fetch account balance of:"+accountId);
		
		try
		{
			return accountService.getAccountBalance(accountId);
		}
		catch(AccountNotExistsException accountNotExistsException)
		{
			final Response response = Response.status(Response.Status.CONFLICT).entity(accountNotExistsException.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
		catch(Exception exception)
		{
			final Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}		
	}
	
	
	@POST
	@Path("/addAccount")
	public Account createAccount(final Account account)
	{
		log.info("Create account - " + account);
		
		try
		{
			return accountService.createAccount(account);
		}
		catch(AccountAlreadyExistsException accountAlreadyExistsException)
		{
			final Response response = Response.status(Response.Status.CONFLICT).entity(accountAlreadyExistsException.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
		catch(Exception exception)
		{
			final Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
	}
	
	
	@POST
	@Path("/transferMoney")
	public Response transferMoney(final Transaction transaction)
	{
		log.info("Transfer money from account :"+transaction.getFromAccountId() + " to account " + transaction.getToAccountId());
		
		try
		{
			accountService.transferFunds(transaction);
		}
		catch(AccountNotExistsException accountNotExistsException)
		{
			final Response response = Response.status(Response.Status.NOT_FOUND).entity(accountNotExistsException.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
		catch(InSufficientMoneyException inSufficientMoneyException)
		{
			final Response response = Response.status(Response.Status.BAD_REQUEST).entity(inSufficientMoneyException.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);
		}
		catch(Exception exception)
		{
			final Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
			
			throw new WebApplicationException(response);			
		}
		
		return Response.status(Response.Status.OK).build();
	}
}
