package edu.hcmute.bookstore.service.impl;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.repository.CategoryRepository;
import edu.hcmute.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryEntity> getCategoryByCatParentId(Long id) {
        return categoryRepository.findAllByCatParent(id);
    }

    @Override
    public CategoryEntity findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).get();
        categoryEntity.setCatStatus(LocalVariable.disableStatus);
        categoryEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> findAllCategoryActive() {
        return categoryRepository.findAllByStatus(LocalVariable.activeStatus);
    }
}
