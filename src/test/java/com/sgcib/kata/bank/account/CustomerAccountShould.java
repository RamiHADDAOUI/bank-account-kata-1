package com.sgcib.kata.bank.account;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sgcib.kata.bank.statement.Statement;
import com.sgcib.kata.bank.transaction.OperationType;
import com.sgcib.kata.bank.transaction.Transaction;

/**
 * @author fahmi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountShould {
    
    @InjectMocks
    private CustomerAccount customerAccount;
    
    @Mock
    private Statement statement;
    
    private static final CurrencyUnit EURO = Monetary.getCurrency("EUR");
    private static final LocalDateTime MOCK_DATE = LocalDateTime.of(2019, 9, 1, 0, 0);
    
    @Before
    public void setUp() {
    	
    }
    
    @Test
    public void make_a_deposit_operation() {
        // GIVEN
    	Money amount = Money.of(100, EURO);
        // WHEN
    	customerAccount.deposit(amount, MOCK_DATE);
        // THEN
        verify(statement, times(1)).add(OperationType.DEPOSIT, amount, MOCK_DATE);
    }
    
    @Test
    public void make_a_deposit_operation_with_negative_amount() {
        // GIVEN
    	Money amount = Money.of(-100, EURO);
        // WHEN
    	customerAccount.deposit(amount, MOCK_DATE);
        // THEN
        verify(statement, times(1)).add(OperationType.DEPOSIT, amount, MOCK_DATE);
        verify(statement, times(0)).addNewStatementLine(new Transaction(amount, LocalDateTime.now(), OperationType.DEPOSIT));
    }
    
}
