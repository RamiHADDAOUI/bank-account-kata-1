package com.sgcib.kata.bank.statement.printer;

import java.util.List;

import com.sgcib.kata.bank.statement.StatementLine;

/**
 * @author fahmi
 *
 */
@FunctionalInterface
public interface StatementPrinter {
	void print(List<StatementLine> statementLines);
}
