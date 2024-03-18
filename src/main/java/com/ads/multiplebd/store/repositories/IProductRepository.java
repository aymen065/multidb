package com.ads.multiplebd.store.repositories;

import com.ads.multiplebd.store.dto.ProductDTO;
import com.ads.multiplebd.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT new com.ads.multiplebd.store.dto.ProductDTO(p.name, p.price) " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId")
    List<ProductDTO> findByCategoryId(Long categoryId);


    List<ProductDTO> findByName(String productName);
    List<ProductDTO> findByCategoryName(String categoryName);

    List<ProductDTO> findByPriceLessThan(Double maxPrice);
    List<ProductDTO> findByPriceGreaterThan(Double minPrice);
    List<ProductDTO> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findAllByIdIn(Set<Long> ids);

    @Query("SELECT new com.ads.multiplebd.store.dto.ProductDTO(p.name, p.price, p.category)" +
            " FROM Product p" +
            " WHERE p.name LIKE %:partialName%")
    List<ProductDTO> findByPartialName(String partialName);




}
