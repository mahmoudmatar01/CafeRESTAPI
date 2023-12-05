package com.fiorecafe.fiore.fiore.repository.best_selling;

import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestSellingItemRepository extends JpaRepository<BestSellingItem,Long> {
}
