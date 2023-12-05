package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.dto.request.AdvRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.UpdateAdvRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.AdvResponseDto;
import com.fiorecafe.fiore.fiore.entity.adv.Advertisement;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.mapper.AdvRequestDtoToAdv;
import com.fiorecafe.fiore.fiore.mapper.AdvertisementToAdvResponseDto;
import com.fiorecafe.fiore.fiore.repository.advertisement.AdvertisementRepository;
import com.fiorecafe.fiore.fiore.service.AdvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvServiceImpl implements AdvService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementToAdvResponseDto toAdvResponseDto;
    private final AdvRequestDtoToAdv advRequestDtoToAdv;
    @Override
    public List<AdvResponseDto> findAll() {
        List<Advertisement>advertisements=advertisementRepository.findAll();
        return advertisements.stream()
                .map(advertisement -> toAdvResponseDto.apply(advertisement))
                .toList();
    }

    @Override
    public AdvResponseDto createAdv(AdvRequestDto requestDto) {
        Advertisement advertisement=advRequestDtoToAdv
                .apply(requestDto);
        advertisementRepository.save(advertisement);
        return toAdvResponseDto
                .apply(advertisement);
    }

    @Override
    public AdvResponseDto update(UpdateAdvRequestDto dto) {
        Advertisement advertisement=advertisementRepository.findById(dto.id()).orElseThrow(
                ()->new NotFoundResourceException("there is no advertisement with this id")
        );
        advertisement.setDescription(dto.newDescription());
        advertisementRepository.save(advertisement);
        return toAdvResponseDto.apply(advertisement);
    }

    @Override
    public AdvResponseDto delAdv(Long id) {
        Advertisement advertisement=advertisementRepository.findById(id).orElseThrow(
                ()->new NotFoundResourceException("there is no advertisement with this id")
        );
        advertisementRepository.deleteById(id);
        return toAdvResponseDto.apply(advertisement);
    }
}
