package com.sgcib.kata.bank.acceptancetests.steps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.assertj.core.api.Assertions;
import org.javamoney.moneta.Money;

import com.sgcib.kata.bank.account.CustomerAccount;
import com.sgcib.kata.bank.statement.Statement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author fahmi
 *
 */
public class CustomerAccountStepDefinitions {
	
	private static final CurrencyUnit EURO = Monetary.getCurrency("EUR");
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private CustomerAccount account;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private Scanner scanner;
	
	@Before
	public void setUp() {
		account = new CustomerAccount(new Statement());
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@Given("^I make a deposit of (\\d+) euros on \"([^\"]*)\"$")
	public void i_make_a_deposit_of(BigDecimal amount, String dateTime) {
	      account.deposit(Money.of(amount, EURO), LocalDateTime.parse(dateTime, dateFormatter));
	}
	
	@Given("^I make a withdraw of (\\d+) euros on \"([^\"]*)\"$")
	public void i_make_a_withdraw_of(BigDecimal amount, String dateTime){
		account.withdraw(Money.of(amount, EURO), LocalDateTime.parse(dateTime, dateFormatter));
	}
	
	@When("^I check the statement$")
	public void i_check_the_statement(){
		outContent.reset();
		account.printHistoryOperations();
	}
	
	@Then("^The number of operations should be (\\d+)$")
	public void the_number_of_operations_should_be(int expectedNumber){
		int nbLines = outContent.toString().split("\n").length;
        Assertions.assertThat(nbLines - 1).isEqualTo(expectedNumber);
	}
	
	@Then("^My balance should be (-?\\d+)")
	public void my_balance_should_be(BigDecimal expectedBalance){
		scanner = new Scanner(outContent.toString());
		String lastPrintedLine = "";
		while (scanner.hasNextLine()) {
			lastPrintedLine = scanner.nextLine();
		}
		BigDecimal value = new BigDecimal(lastPrintedLine.split("\\|")[3].replace("€", "").replace(",", ".").trim()).setScale(1);
        Assertions.assertThat(value).isEqualTo(expectedBalance);
	}
}
