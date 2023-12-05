package com.fiorecafe.fiore.fiore.repository.user;

import com.fiorecafe.fiore.fiore.entity.user.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage,Long> {

    Optional<UserImage>findByTitle(String title);
}
