package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.entity.adv.AdvImage;
import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItemImage;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.best_selling.BestSellingItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.fiorecafe.fiore.fiore.utils.ImageUtils.compressImage;
import static com.fiorecafe.fiore.fiore.utils.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class BestSellingImageServiceImpl {

    private final BestSellingItemImageRepository bestSellingItemImageRepository;
    public BestSellingItemImage uploadImage(MultipartFile file) throws IOException {
        BestSellingItemImage image = bestSellingItemImageRepository.save(
                BestSellingItemImage.builder()
                        .title(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(compressImage(file.getBytes()))
                        .photoUrl(generateImageUrl(file.getOriginalFilename()))
                        .build()
        );

        if (image != null) {
            return image;
        }

        return null;
    }

    public byte[] downloadImage(String imageName) {
        BestSellingItemImage dbImage = bestSellingItemImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        byte[]image=decompressImage(dbImage.getData());
        return image;
    }

    public BestSellingItemImage getImageDataByName(String imageName) {
        BestSellingItemImage dbImage = bestSellingItemImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        return dbImage;
    }

    public String generateImageUrl(String imageName) {
        return "http://localhost:5920/api/v1/best-items/image/" + imageName;
    }
}
