package com.ads.multiplebd.store.services;

import com.ads.multiplebd.store.dto.OrderDTO;
import com.ads.multiplebd.store.dto.ProductDTO;
import com.ads.multiplebd.store.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IProductService {

    List<ProductDTO> findByName(String productName);
    List<ProductDTO> findByCategoryId(Long categoryId);
    List<ProductDTO> findByCategoryName(String categoryName);
    List<ProductDTO> findByPriceLessThan(Double maxPrice);
    List<ProductDTO> findByPriceGreaterThan(Double minPrice);
    List<ProductDTO> findByPriceBetween(Double minPrice, Double maxPrice);
    List<ProductDTO> findByPartialName(String partialName);

    List<Product> findAllByIdIn(Set<Long> ids);

    Page<ProductDTO> findAllProducts(Integer page, Integer size);
    ProductDTO findById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);


    void deleteProductById(Long id);
    void deleteAllProducts();
}
