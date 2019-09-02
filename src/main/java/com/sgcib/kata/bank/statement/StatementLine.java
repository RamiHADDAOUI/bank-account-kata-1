package com.sgcib.kata.bank.statement;

import javax.money.MonetaryAmount;

import com.sgcib.kata.bank.transaction.Transaction;

/**
 * @author fahmi
 *
 */
public class StatementLine {

	private Transaction transaction;

	private MonetaryAmount balance;

	public StatementLine(Transaction transaction, MonetaryAmount balance) {
		this.transaction = transaction;
		this.balance = balance;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public MonetaryAmount getBalance() {
		return balance;
	}

}
