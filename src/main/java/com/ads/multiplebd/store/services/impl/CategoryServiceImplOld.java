package com.ads.multiplebd.store.services.impl;

import com.ads.multiplebd.store.dto.CategoryDTO;
import com.ads.multiplebd.store.models.Category;
import com.ads.multiplebd.store.repositories.ICategoryRepository;
import com.ads.multiplebd.store.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Lazy
@Scope("singleton")
@Service
@AllArgsConstructor
public class CategoryServiceImplOld implements ICategoryService {
    private ICategoryRepository categoryRepository;
    private Mapper mapper;
    @Override
    public Page<CategoryDTO> findAllCategories(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        System.out.println("from category service");
        return categoryRepository.findAll(paging).map(category -> mapper.map(category, CategoryDTO.class));
    }

    @Override
    public CategoryDTO findById(Long id) {
        return mapper.map(categoryRepository.findById(id), CategoryDTO.class);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return mapper.map(categoryRepository.save(new Category(categoryDTO)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return categoryRepository.findById(categoryDTO.getId())
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    return mapper.map(categoryRepository.save(category), CategoryDTO.class);
                }).orElse(null);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
