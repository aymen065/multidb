package com.ads.multiplebd.store.repositories;

import com.ads.multiplebd.store.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {


}
