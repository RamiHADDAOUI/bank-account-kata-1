package com.sgcib.kata.bank.statement;

import java.time.LocalDateTime;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.assertj.core.api.Assertions;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sgcib.kata.bank.transaction.OperationType;
import com.sgcib.kata.bank.transaction.Transaction;

/**
 * @author fahmi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StatementShould {

	private Statement statement;
	
	private static final CurrencyUnit EURO = Monetary.getCurrency("EUR");
	private static final LocalDateTime MOCK_DATE = LocalDateTime.of(2019, 9, 1, 0, 0);
	
	@Before
    public void setUp() {
        this.statement = new Statement();
    }
	
	@Test
    public void add_and_save_operation_in_history_operations() {
        // GIVEN
		Money amount = Money.of(100, EURO);
        // WHEN
		statement.add(OperationType.DEPOSIT, amount, MOCK_DATE);
        // THEN
        Assertions.assertThat(statement.contains(new Transaction(amount, MOCK_DATE, OperationType.DEPOSIT))).isTrue();
    }
	
}
