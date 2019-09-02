package com.sgcib.kata.bank.transaction;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.money.MonetaryAmount;

/**
 * @author fahmi
 *
 */
public class Transaction {

	private MonetaryAmount amount;
	private LocalDateTime date;
	private OperationType operationType;
	
	public Transaction(MonetaryAmount amount, LocalDateTime date, OperationType operationType) {
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return amount.equals(transaction.amount) &&
                date.equals(transaction.date) &&
                operationType == transaction.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, operationType);
    }

}
