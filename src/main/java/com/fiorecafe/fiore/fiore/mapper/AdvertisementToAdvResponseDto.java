package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.AdvResponseDto;
import com.fiorecafe.fiore.fiore.entity.adv.Advertisement;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AdvertisementToAdvResponseDto implements Function<Advertisement, AdvResponseDto> {
    @Override
    public AdvResponseDto apply(Advertisement advertisement) {
        return AdvResponseDto.builder()
                .advId(advertisement.getId())
                .advDescription(advertisement.getDescription())
                .advImageUrl(advertisement.getAdvImageUrl())
                .build();
    }
}
