package com.fiorecafe.fiore.fiore.repository.item;

import com.fiorecafe.fiore.fiore.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM items WHERE category_id = :categoryId", nativeQuery = true)
    List<Item> findItemsByCategoryId(@Param("categoryId") Long categoryId);
}
