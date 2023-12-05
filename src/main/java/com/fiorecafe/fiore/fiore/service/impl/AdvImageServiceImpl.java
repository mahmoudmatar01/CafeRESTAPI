package com.fiorecafe.fiore.fiore.service.impl;
import com.fiorecafe.fiore.fiore.entity.adv.AdvImage;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.advertisement.AdvImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.fiorecafe.fiore.fiore.utils.ImageUtils.compressImage;
import static com.fiorecafe.fiore.fiore.utils.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class AdvImageServiceImpl {
    private final AdvImageRepository advImageRepository;

    public AdvImage uploadImage(MultipartFile file) throws IOException {
        AdvImage image = advImageRepository.save(
                AdvImage.builder()
                        .title(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(compressImage(file.getBytes()))
                        .advImageUrl(generateImageUrl(file.getOriginalFilename()))
                        .build()
        );

        if (image != null) {
            return image;
        }

        return null;
    }

    public byte[] downloadImage(String imageName) {
        AdvImage dbImage = advImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        byte[]image=decompressImage(dbImage.getData());
        return image;
    }

    public AdvImage getImageDataByName(String imageName) {
        AdvImage dbImage = advImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no image with this name")
        );
        return dbImage;
    }

    public String generateImageUrl(String imageName) {
        return "http://localhost:5920/api/v1/adv/image/" + imageName;
    }
}
