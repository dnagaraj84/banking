/**
 * 
 */
package com.revolut.banking.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nags
 *
 */
public class Account 
{
	@JsonProperty(required = true)
    @NotNull
	private String accountId;
	
	@JsonProperty(required = false)
	private User accountHolder;
	
	@JsonProperty(required = true)
	private BigDecimal accountBalance;	
	
	public Account() 
	{
		this(builder());
	}


	private Account(final Builder builder) 
	{
	    this(builder.accountId, builder.accountHolder, builder.accountBalance);
	}

	
	public Account(String accountId, User accountHolder, BigDecimal accountBalance) 
	{
		super();
		this.accountId = accountId;
		this.accountHolder = accountHolder;
		this.accountBalance = accountBalance;
	}
	
	public static Builder builder() 
	{
		return new Builder();
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public User getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(User accountHolder) {
		this.accountHolder = accountHolder;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}	

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountHolder=" + accountHolder + ", accountBalance="
				+ accountBalance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (accountHolder == null) {
			if (other.accountHolder != null)
				return false;
		} else if (!accountHolder.equals(other.accountHolder))
			return false;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		return true;
	}
	

	public static class Builder 
	{
		private String accountId;
		
		private User accountHolder;
		
		private BigDecimal accountBalance;
		
		public Builder(){}
		
		public Builder setAccountNumber(String accountId)
		{
			this.accountId = accountId;
			
			return this;
		}
		
		public Builder setAccountHolder(User accountHolder)
		{
			this.accountHolder = accountHolder;
			
			return this;
		}
		
		public Builder setAccountBalance(BigDecimal accountBalance)
		{
			this.accountBalance = accountBalance;
			
			return this;
		}
		
		
		public Account build()
		{
			return new Account(accountId, accountHolder, accountBalance);
		}
	}

}
