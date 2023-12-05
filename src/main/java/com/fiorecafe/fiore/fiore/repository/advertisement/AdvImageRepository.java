package com.fiorecafe.fiore.fiore.repository.advertisement;

import com.fiorecafe.fiore.fiore.entity.adv.AdvImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvImageRepository extends JpaRepository<AdvImage,Long> {
    Optional<AdvImage>findByTitle(String title);
}
