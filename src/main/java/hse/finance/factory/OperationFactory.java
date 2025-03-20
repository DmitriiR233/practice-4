package hse.finance.factory;

import hse.finance.domain.BankAccount;
import hse.finance.domain.Category;
import hse.finance.domain.Operation;

import java.util.Date;

public class OperationFactory {
    private static Long idCounter = 1L;

    public Operation createOperation(String type, BankAccount bankAccount, Double amount, Date date, String description, Category category) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return new Operation(idCounter++, type, bankAccount, amount, date, description, category);
    }
}
