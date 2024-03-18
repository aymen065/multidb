package com.ads.multiplebd.store.dto;

import com.ads.multiplebd.store.models.Category;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Category category;

    public ProductDTO(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public ProductDTO(String name, Double price) {
        this.name = name;
        this.price = price;

    }
}
