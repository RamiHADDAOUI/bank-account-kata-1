package com.sgcib.kata.bank.statement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

import com.sgcib.kata.bank.transaction.OperationType;
import com.sgcib.kata.bank.transaction.Transaction;

/**
 * @author fahmi
 *
 */
public class Statement {

	private List<StatementLine> statementLines = new ArrayList<>();
	
	public Statement() {
		this.statementLines = new ArrayList<>();
	}
	
	public List<StatementLine> getStatementLines() {
        return Collections.unmodifiableList(this.statementLines);
    }
	
	public void add(OperationType operationType, MonetaryAmount amount, LocalDateTime dateTime) {
		Transaction transaction = new Transaction(amount, dateTime, operationType);
		if (amount.isPositive())
			addNewStatementLine(transaction);
		else
			System.err.println("Impossible transaction : negative amount");
	}

	public void addNewStatementLine(Transaction transaction) {
		this.statementLines.add(new StatementLine(transaction, getBalance().add((transaction.getAmount()))));
	} 
	
	public MonetaryAmount getBalance() {
        return this.statementLines.isEmpty() ? Money.of(0, Monetary.getCurrency("EUR")) : this.statementLines.get(this.statementLines.size() - 1).getBalance();
    } 
	
	public boolean contains(Transaction transaction) {
		List<Transaction> transactions = this.statementLines.stream().map(statementLine -> statementLine.getTransaction()).collect(Collectors.toList());
		return transactions.contains(transaction);
	}

}
