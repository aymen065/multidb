package com.ads.multiplebd.store.services;

import com.ads.multiplebd.store.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {

    Page<CategoryDTO> findAllCategories(Integer page, Integer size);
    CategoryDTO findById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);


    void deleteCategoryById(Long id);
    void deleteAllCategories();

}
