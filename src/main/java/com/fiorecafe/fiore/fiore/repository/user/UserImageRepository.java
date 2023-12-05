package com.fiorecafe.fiore.fiore.repository.user;

import com.fiorecafe.fiore.fiore.entity.user.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage,Long> {

    Optional<UserImage>findByTitle(String title);
}
