package hse.finance;

import hse.finance.domain.Category;
import hse.finance.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryService();
    }

    @Test
    @DisplayName("Проверка создания категории")
    public void testCreateCategory() {
        Category category = new Category(1L, "Доход", "Зарплата");
        Category createdCategory = categoryService.createCategory(category);
        assertNotNull(createdCategory.getId());
        assertEquals("Доход", createdCategory.getType());
        assertEquals("Зарплата", createdCategory.getName());
    }

    @Test
    @DisplayName("Проверка обновления категории")
    public void testUpdateCategory() {
        Category category = new Category(1L, "Доход", "Зарплата");
        categoryService.createCategory(category);

        category.setType("Расход");
        category.setName("Продукты");
        Category updatedCategory = categoryService.updateCategory(category);

        assertEquals("Расход", updatedCategory.getType());
        assertEquals("Продукты", updatedCategory.getName());
    }

    @Test
    @DisplayName("Проверка удаления категории")
    public void testDeleteCategory() {
        Category category = new Category(1L, "Доход", "Зарплата");
        categoryService.createCategory(category);

        categoryService.deleteCategory(1L);
        assertNull(categoryService.getCategoryById(1L));
    }

    @Test
    @DisplayName("Проверка получения всех категорий")
    public void testGetAllCategories() {
        Category category1 = new Category(1L, "Доход", "Зарплата");
        Category category2 = new Category(2L, "Расход", "Продукты");
        categoryService.createCategory(category1);
        categoryService.createCategory(category2);

        assertEquals(2, categoryService.getAllCategories().size());
    }

    @Test
    @DisplayName("Проверка получения категории по ID")
    public void testGetCategoryById() {
        Category category = new Category(1L, "Доход", "Зарплата");
        categoryService.createCategory(category);

        Category retrievedCategory = categoryService.getCategoryById(1L);
        assertNotNull(retrievedCategory);
        assertEquals("Доход", retrievedCategory.getType());
        assertEquals("Зарплата", retrievedCategory.getName());
    }
}