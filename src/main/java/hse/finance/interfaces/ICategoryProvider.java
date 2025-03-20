package hse.finance.interfaces;

import hse.finance.domain.Category;

public interface ICategoryProvider {
    Category getCategoryById(Long id);
}
