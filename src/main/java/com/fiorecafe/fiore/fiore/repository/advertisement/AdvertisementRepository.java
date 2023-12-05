package com.fiorecafe.fiore.fiore.repository.advertisement;

import com.fiorecafe.fiore.fiore.entity.adv.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
}
