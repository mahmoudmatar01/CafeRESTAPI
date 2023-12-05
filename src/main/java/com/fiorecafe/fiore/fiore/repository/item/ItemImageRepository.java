package com.fiorecafe.fiore.fiore.repository.item;

import com.fiorecafe.fiore.fiore.entity.item.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage,Long> {
    Optional<ItemImage> findByTitle(String title);
}
