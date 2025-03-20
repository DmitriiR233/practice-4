package hse.finance;

import hse.finance.domain.BankAccount;
import hse.finance.domain.Category;
import hse.finance.domain.Operation;
import hse.finance.factory.OperationFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OperationFactoryTest {
    private OperationFactory operationFactory = new OperationFactory();

    @Test
    @DisplayName("Проверка создания операции")
    public void testCreateOperation() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        Category category = new Category(1L, "Доход", "Зарплата");
        Operation operation = operationFactory.createOperation("Доход", bankAccount, 500.0, new Date(), "Зарплата за месяц", category);
        assertNotNull(operation.getId());
        assertEquals("Доход", operation.getType());
        assertEquals(500.0, operation.getAmount());
        assertEquals("Зарплата за месяц", operation.getDescription());
    }

    @Test
    @DisplayName("Проверка создания операции с отрицательной суммой")
    public void testCreateOperationWithNegativeAmount() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        Category category = new Category(1L, "Доход", "Зарплата");
        assertThrows(IllegalArgumentException.class, () -> {
            operationFactory.createOperation("Доход", bankAccount, -500.0, new Date(), "Зарплата за месяц", category);
        });
    }
}
