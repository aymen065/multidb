package com.ads.multiplebd.store.services.impl;

import com.ads.multiplebd.store.dto.ProductDTO;
import com.ads.multiplebd.store.models.Product;
import com.ads.multiplebd.store.repositories.ICategoryRepository;
import com.ads.multiplebd.store.repositories.IProductRepository;
import com.ads.multiplebd.store.services.IProductService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    private IProductRepository productRepository;
    private ICategoryRepository categoryRepository ;
    private Mapper mapper;

    @Override
    public List<ProductDTO> findByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductDTO> findByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<ProductDTO> findByPriceLessThan(Double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    @Override
    public List<ProductDTO> findByPriceGreaterThan(Double minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    @Override
    public List<ProductDTO> findByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }

    @Override
    public List<ProductDTO> findByPartialName(String partialName) {
        return productRepository.findByPartialName(partialName);
    }

    @Override
    public List<Product> findAllByIdIn(Set<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }


    @Override
    public Page<ProductDTO> findAllProducts(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return productRepository.findAll(paging).map(product -> mapper.map(product,ProductDTO.class));
    }

    @Override
    public ProductDTO findById(Long id) {
        return mapper.map(productRepository.findById(id),ProductDTO.class);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        product.setCategory(categoryRepository.findById(productDTO.getCategory().getId()).orElse(null));
        return mapper.map(productRepository.save(product),ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return productRepository.findById(productDTO.getId()).map(product -> {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            return mapper.map(product,ProductDTO.class);
        }).orElse(null);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
