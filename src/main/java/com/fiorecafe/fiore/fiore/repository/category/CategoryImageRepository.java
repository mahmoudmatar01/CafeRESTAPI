package com.fiorecafe.fiore.fiore.repository.category;
import com.fiorecafe.fiore.fiore.entity.category.CategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage,Integer> {

    Optional<CategoryImage>findByTitle(String title);

    @Query(value = "SELECT * FROM category_images WHERE category_id = :categoryId", nativeQuery = true)
    Optional<CategoryImage>findByCategoryId(@Param("categoryId") Long categoryId);
}
