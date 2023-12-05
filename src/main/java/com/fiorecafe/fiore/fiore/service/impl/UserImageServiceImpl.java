package com.fiorecafe.fiore.fiore.service.impl;
import com.fiorecafe.fiore.fiore.entity.user.UserImage;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.user.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.fiorecafe.fiore.fiore.utils.ImageUtils.compressImage;
import static com.fiorecafe.fiore.fiore.utils.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl {
    private final UserImageRepository userImageRepository;

    public UserImage uploadImage(MultipartFile file) throws IOException {
        UserImage image = userImageRepository.save(
                UserImage.builder()
                        .title(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(compressImage(file.getBytes()))
                        .userPhotoUrl(generateImageUrl(file.getOriginalFilename()))
                        .build()
        );
        return image;
    }

    public byte[] downloadImage(String imageName) {
        UserImage dbImage = userImageRepository.findByTitle(imageName).orElseThrow(
                () -> new NotFoundResourceException("There is no user image with this name")
        );
        return decompressImage(dbImage.getData());
    }

    public UserImage getImageDataByName(String imageName) {
        return userImageRepository.findByTitle(imageName).orElseThrow(
                () -> new NotFoundResourceException("There is no user image with this name")
        );
    }

    public String generateImageUrl(String imageName) {
        return "http://localhost:5920/api/v1/auth/image/" + imageName;
    }
}
