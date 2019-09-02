Feature: Bank Account

Scenario: Multiple Deposit / Withdraw and check the number of operations and the current balance
    Given I make a deposit of 500 euros on "2019-09-02 11:29:28"
    And I make a withdraw of 200 euros on "2019-09-05 15:30:00"
    When I check the statement
    Then The number of operations should be 2
    And My balance should be 300
   