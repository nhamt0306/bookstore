package edu.hcmute.bookstore.service;

import edu.hcmute.bookstore.model.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategory();
    List<CategoryEntity> getCategoryByCatParentId(Long id);
    CategoryEntity findCategoryById(Long id);
    CategoryEntity save(CategoryEntity categoryEntity);
    void deleteCategoryById(Long id);
    List<CategoryEntity> findAllCategoryActive();
}
