package com.sgcib.kata.bank.statement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	
	@Test
    public void make_several_operations_and_generate_statement() {
        // GIVEN
		statement.add(OperationType.DEPOSIT, Money.of(100, EURO), MOCK_DATE);
		statement.add(OperationType.DEPOSIT, Money.of(150, EURO), MOCK_DATE);
		statement.add(OperationType.WITHDRAW, Money.of(50, EURO).negate(), MOCK_DATE);
		
		List<StatementLine> expectedResult = new ArrayList<StatementLine>();
		expectedResult.add(new StatementLine(new Transaction(Money.of(100, EURO), MOCK_DATE, OperationType.DEPOSIT), Money.of(100, EURO)));
		expectedResult.add(new StatementLine(new Transaction(Money.of(150, EURO), MOCK_DATE, OperationType.DEPOSIT), Money.of(250, EURO)));
		expectedResult.add(new StatementLine(new Transaction(Money.of(50, EURO).negate(), MOCK_DATE, OperationType.WITHDRAW), Money.of(200, EURO)));
		 // WHEN
		List<StatementLine> result = statement.getStatementLines();
		// THEN
		Assertions.assertThat(result).hasSize(3);
		
		Assertions.assertThat(result.get(0)).isEqualTo(expectedResult.get(0));
        Assertions.assertThat(result.get(1)).isEqualTo(expectedResult.get(1));
        Assertions.assertThat(result.get(2)).isEqualTo(expectedResult.get(2));
    }
	
}
