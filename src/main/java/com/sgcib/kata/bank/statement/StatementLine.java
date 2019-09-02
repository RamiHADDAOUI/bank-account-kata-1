package com.sgcib.kata.bank.statement;

import java.util.Objects;

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

	public void print() {
		this.transaction.print(balance);
	}
	
	@Override
    public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StatementLine statementLine = (StatementLine) o;
		return this.balance.equals(statementLine.balance) && this.transaction.equals(statementLine.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction, balance);
    }
    
}
