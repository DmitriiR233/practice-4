package hse.finance.factory;

import hse.finance.domain.Category;

public class CategoryFactory {
    private static Long idCounter = 1L;

    public Category createCategory(String type, String name) {
        return new Category(idCounter++, type, name);
    }
}