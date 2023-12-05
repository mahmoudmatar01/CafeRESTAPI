package com.fiorecafe.fiore.fiore.service.impl;
import com.fiorecafe.fiore.fiore.entity.category.CategoryImage;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.category.CategoryImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.fiorecafe.fiore.fiore.utils.ImageUtils.compressImage;
import static com.fiorecafe.fiore.fiore.utils.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class CategoryImageServiceImpl {

    private final CategoryImageRepository categoryImageRepository;

    public CategoryImage uploadImage(MultipartFile file) throws IOException {
        CategoryImage image = categoryImageRepository.save(
                CategoryImage.builder()
                        .title(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(compressImage(file.getBytes()))
                        .categoryImageUrl(generateImageUrl(file.getOriginalFilename()))
                        .build()
        );

        if (image != null) {
            return image;
        }

        return null;
    }

    public byte[] downloadImage(String imageName) {
        CategoryImage dbImage = categoryImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        byte[]image=decompressImage(dbImage.getData());
        return image;
    }

    public CategoryImage getImageDataByName(String imageName) {
        CategoryImage dbImage = categoryImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        return dbImage;
    }

    public String generateImageUrl(String imageName) {
        return "http://localhost:5920/api/v1/category/image/" + imageName;
    }
}
