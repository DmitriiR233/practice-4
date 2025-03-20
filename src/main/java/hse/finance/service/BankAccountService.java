package hse.finance.service;

import hse.finance.domain.BankAccount;
import hse.finance.interfaces.IBankAccountProvider;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountProvider {
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount createBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (bankAccounts.get(i).getId().equals(bankAccount.getId())) {
                bankAccounts.set(i, bankAccount);
                return bankAccount;
            }
        }
        return null;
    }

    public void deleteBankAccount(Long id) {
        bankAccounts.removeIf(bankAccount -> bankAccount.getId().equals(id));
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getId().equals(id)).findFirst().orElse(null);
    }
}
