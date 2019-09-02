Feature: Bank Account

Scenario: Multiple Deposit / Withdraw and check the number of operations and the current balance
    Given I make a deposit of 500 euros on "2019-09-02 11:29:28"
    And I make a withdraw of 200 euros on "2019-09-05 15:30:00"
    When I check the statement
    Then The number of operations should be 2
    And My balance should be 300
    
Scenario: Multiple Deposit / Withdraw and check the operations history
    Given I make a deposit of 100 euros on "2019-09-03 12:00:00"
    And I make a withdraw of 50 euros on "2019-09-10 18:45:45"
    When I check the statement
    Then I would see 
    """
    OPERATION   |  DATE   |  AMOUNT  |  BALANCE
	DEPOSIT | 2019-09-03 12:00:00 | 100,00 € | 100,00 €
	WITHDRAW | 2019-09-10 18:45:45 | -50,00 € | 50,00 €
    """
   