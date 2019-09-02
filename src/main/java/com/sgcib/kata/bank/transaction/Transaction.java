package com.sgcib.kata.bank.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.javamoney.moneta.format.CurrencyStyle;

/**
 * @author fahmi
 *
 */
public class Transaction {
	
	private static final String COLUMN_SEPARATOR = " | ";
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final MonetaryAmountFormat amountFormatter = MonetaryFormats.getAmountFormat(
		      AmountFormatQueryBuilder.of(Locale.FRANCE)
		        .set(CurrencyStyle.SYMBOL)
		        .build()
		    );


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
    
    public void print(MonetaryAmount balance) {
		System.out.println(this.getOperationType().name()
                + COLUMN_SEPARATOR
                + dateFormatter.format(this.getDate())
                + COLUMN_SEPARATOR
                + amountFormatter.format(this.getAmount())
                + COLUMN_SEPARATOR
                + amountFormatter.format(balance)
        );
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
