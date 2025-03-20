package hse.finance;

import hse.finance.domain.BankAccount;
import hse.finance.factory.BankAccountFactory.BankAccountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountFactoryTest {
    private BankAccountFactory bankAccountFactory = new BankAccountFactory();

    @Test
    @DisplayName("Проверка создания банковского счета")
    public void testCreateBankAccount() {
        BankAccount bankAccount = bankAccountFactory.createBankAccount("Основной счет", 1000.0);
        assertNotNull(bankAccount.getId());
        assertEquals("Основной счет", bankAccount.getName());
        assertEquals(1000.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Проверка создания банковского счета с отрицательным балансом")
    public void testCreateBankAccountWithNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            bankAccountFactory.createBankAccount("Основной счет", -1000.0);
        });
    }
}
