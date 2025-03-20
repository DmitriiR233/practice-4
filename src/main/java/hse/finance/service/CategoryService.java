package hse.finance.service;

import hse.finance.domain.Category;
import hse.finance.interfaces.ICategoryProvider;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryProvider {
    private List<Category> categories = new ArrayList<>();

    public Category createCategory(Category category) {
        categories.add(category);
        return category;
    }

    public Category updateCategory(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())) {
                categories.set(i, category);
                return category;
            }
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categories.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }
}
