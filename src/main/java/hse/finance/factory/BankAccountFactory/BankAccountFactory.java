package hse.finance.factory.BankAccountFactory;

import hse.finance.domain.BankAccount;

public class BankAccountFactory {
    private static Long idCounter = 1L;

    public BankAccount createBankAccount(String name, Double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return new BankAccount(idCounter++, name, balance);
    }
}
