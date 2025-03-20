package hse.finance.interfaces;

import hse.finance.domain.BankAccount;

public interface IBankAccountProvider {
    BankAccount getBankAccountById(Long id);
}
