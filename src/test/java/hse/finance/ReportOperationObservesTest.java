package hse.finance;

import hse.finance.domain.BankAccount;
import hse.finance.domain.Category;
import hse.finance.domain.Operation;
import hse.finance.report.ReportOperationObserves;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReportOperationObservesTest {
    private ReportOperationObserves reportOperationObserves;

    @BeforeEach
    public void setUp() {
        reportOperationObserves = new ReportOperationObserves();
    }

    @Test
    @DisplayName("Проверка обновления операции")
    public void testUpdate() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        Category category = new Category(1L, "Доход", "Зарплата");
        Operation operation = new Operation(1L, "Доход", bankAccount, 500.0, new Date(), "Зарплата за месяц", category);
        reportOperationObserves.update(operation);

        assertEquals(1, reportOperationObserves.getOperations().size());
        assertEquals(operation, reportOperationObserves.getOperations().get(0));
    }

    @Test
    @DisplayName("Проверка обновления нескольких операций")
    public void testUpdateMultipleOperations() {
        BankAccount bankAccount = new BankAccount(1L, "Основной счет", 1000.0);
        Category category = new Category(1L, "Доход", "Зарплата");
        Operation operation1 = new Operation(1L, "Доход", bankAccount, 500.0, new Date(), "Зарплата за месяц", category);
        Operation operation2 = new Operation(2L, "Расход", bankAccount, 300.0, new Date(), "Покупка продуктов", category);
        reportOperationObserves.update(operation1);
        reportOperationObserves.update(operation2);

        assertEquals(2, reportOperationObserves.getOperations().size());
        assertEquals(operation1, reportOperationObserves.getOperations().get(0));
        assertEquals(operation2, reportOperationObserves.getOperations().get(1));
    }

    @Test
    @DisplayName("Проверка обновления без операций")
    public void testUpdateWithNoOperations() {
        assertEquals(0, reportOperationObserves.getOperations().size());
    }
}
