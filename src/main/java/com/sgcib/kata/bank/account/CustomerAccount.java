package com.sgcib.kata.bank.account;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;

import com.sgcib.kata.bank.statement.Statement;
import com.sgcib.kata.bank.transaction.OperationType;

/**
 * @author fahmi
 *
 */
public class CustomerAccount implements HistorizedAccount {

	private Statement statement;
    
    public CustomerAccount(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void deposit(MonetaryAmount amount, LocalDateTime dateTime) {
    	statement.add(OperationType.DEPOSIT, amount, dateTime);
    }

    @Override
    public void withdraw(MonetaryAmount amount, LocalDateTime dateTime) {
    	//TODO
    }
    
	@Override
	public void printHistoryOperations() {
		//TODO
	}
    
}
