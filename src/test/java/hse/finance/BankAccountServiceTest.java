package hse.finance;

import hse.finance.domain.BankAccount;
import hse.finance.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountServiceTest {
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setUp() {
        bankAccountService = new BankAccountService();
    }

    @Test
    @DisplayName("Проверка создания банковского счета")
    public void testCreateBankAccount() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        assertNotNull(createdBankAccount.getId());
        assertEquals("Основной счет", createdBankAccount.getName());
        assertEquals(1000.0, createdBankAccount.getBalance());
    }

    @Test
    @DisplayName("Проверка обновления банковского счета")
    public void testUpdateBankAccount() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        bankAccountService.createBankAccount(bankAccount);

        bankAccount.setName("Сберегательный счет");
        bankAccount.setBalance(2000.0);
        BankAccount updatedBankAccount = bankAccountService.updateBankAccount(bankAccount);

        assertEquals("Сберегательный счет", updatedBankAccount.getName());
        assertEquals(2000.0, updatedBankAccount.getBalance());
    }

    @Test
    @DisplayName("Проверка удаления банковского счета")
    public void testDeleteBankAccount() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        bankAccountService.createBankAccount(bankAccount);

        bankAccountService.deleteBankAccount(1L);
        assertNull(bankAccountService.getBankAccountById(1L));
    }

    @Test
    @DisplayName("Проверка получения всех банковских счетов")
    public void testGetAllBankAccounts() {
        BankAccount bankAccount1 = new BankAccount(1L, "Основной счет", 1000.0);
        BankAccount bankAccount2 = new BankAccount(2L, "Сберегательный счет", 2000.0);
        bankAccountService.createBankAccount(bankAccount1);
        bankAccountService.createBankAccount(bankAccount2);

        assertEquals(2, bankAccountService.getAllBankAccounts().size());
    }

    @Test
    @DisplayName("Проверка получения банковского счета по ID")
    public void testGetBankAccountById() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        bankAccountService.createBankAccount(bankAccount);

        BankAccount retrievedBankAccount = bankAccountService.getBankAccountById(1L);
        assertNotNull(retrievedBankAccount);
        assertEquals("Основной счет", retrievedBankAccount.getName());
        assertEquals(1000.0, retrievedBankAccount.getBalance());
    }
}
