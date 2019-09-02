package com.sgcib.kata.bank.account;

import java.time.LocalDateTime;
import java.util.List;

import javax.money.MonetaryAmount;

import com.sgcib.kata.bank.statement.Statement;
import com.sgcib.kata.bank.statement.StatementLine;
import com.sgcib.kata.bank.statement.printer.StatementPrinter;
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
    	statement.add(OperationType.WITHDRAW, amount.negate(), dateTime);
    }
    
	@Override
	public void printHistoryOperations() {
		StatementPrinter s = (List<StatementLine> statementLines) -> {
			System.out.println("OPERATION   |  DATE   |  AMOUNT  |  BALANCE");
			statementLines.stream().forEach(statementLine -> statementLine.print());
		};
		s.print(statement.getStatementLines());
	}
    
}
