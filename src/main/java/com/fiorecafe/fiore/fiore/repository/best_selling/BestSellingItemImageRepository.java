package com.fiorecafe.fiore.fiore.repository.best_selling;

import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BestSellingItemImageRepository extends JpaRepository<BestSellingItemImage,Long> {
    Optional<BestSellingItemImage>findByTitle(String title);
}
