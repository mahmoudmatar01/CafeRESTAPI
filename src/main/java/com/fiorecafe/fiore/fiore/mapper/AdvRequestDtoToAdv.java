package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.request.AdvRequestDto;
import com.fiorecafe.fiore.fiore.entity.adv.AdvImage;
import com.fiorecafe.fiore.fiore.entity.adv.Advertisement;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.service.impl.AdvImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AdvRequestDtoToAdv implements Function<AdvRequestDto, Advertisement> {

    private final AdvImageServiceImpl advImageService;
    @Override
    public Advertisement apply(AdvRequestDto advRequestDto) {
        try {
            AdvImage advImage=advImageService.uploadImage(advRequestDto.image());
            return Advertisement.builder()
                    .description(advRequestDto.advDescription())
                    .advImage(advImage)
                    .advImageUrl(advImage.getAdvImageUrl())
                    .build();
        } catch (IOException e) {
            throw new BadRequestException("something went wrong");
        }
    }
}
