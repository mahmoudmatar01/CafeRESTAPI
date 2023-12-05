package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.entity.item.ItemImage;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.item.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.fiorecafe.fiore.fiore.utils.ImageUtils.compressImage;
import static com.fiorecafe.fiore.fiore.utils.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class ItemImageServiceImpl {
    private final ItemImageRepository itemImageRepository;

    public ItemImage uploadImage(MultipartFile file) throws IOException {
        ItemImage image = itemImageRepository.save(
                ItemImage.builder()
                        .title(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(compressImage(file.getBytes()))
                        .itemPhotoUrl(generateImageUrl(file.getOriginalFilename()))
                        .build()
        );

        if (image != null) {
            return image;
        }

        return null;
    }

    public byte[] downloadImage(String imageName) {
        ItemImage dbImage = itemImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no item image with this name")
        );
        byte[]image=decompressImage(dbImage.getData());
        return image;
    }

    public ItemImage getImageDataByName(String imageName) {
        ItemImage dbImage = itemImageRepository.findByTitle(imageName).orElseThrow(
                ()->new NotFoundResourceException("there is no item image with this name")
        );
        return dbImage;
    }

    public String generateImageUrl(String imageName) {
        return "http://localhost:5920/api/v1/items/image/" + imageName;
    }
}
