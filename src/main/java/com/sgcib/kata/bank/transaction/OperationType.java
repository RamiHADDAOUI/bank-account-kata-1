package com.sgcib.kata.bank.transaction;

/**
 * @author fahmi
 *
 */
public enum OperationType {

    DEPOSIT("deposit operation"),
    WITHDRAW("withdraw operation");

    private String operationName;

    OperationType(String operationName) {
        this.operationName = operationName;
    }
    
    @Override
    public String toString() {
    	return operationName;
    }
}
