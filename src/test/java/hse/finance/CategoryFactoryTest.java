package hse.finance;

import hse.finance.domain.Category;
import hse.finance.factory.CategoryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryFactoryTest {
    private CategoryFactory categoryFactory = new CategoryFactory();

    @Test
    @DisplayName("Проверка создания категории")
    public void testCreateCategory() {
        Category category = categoryFactory.createCategory("Доход", "Зарплата");
        assertNotNull(category.getId());
        assertEquals("Доход", category.getType());
        assertEquals("Зарплата", category.getName());
    }
}
