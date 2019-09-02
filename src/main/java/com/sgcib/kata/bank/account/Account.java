package com.sgcib.kata.bank.account;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;

/**
 * @author fahmi
 *
 */
public interface Account {
	void deposit(MonetaryAmount amount, LocalDateTime dateTime);
	void withdraw(MonetaryAmount amount, LocalDateTime dateTime);
}
