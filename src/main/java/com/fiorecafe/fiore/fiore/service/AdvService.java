package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.AdvRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.UpdateAdvRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.AdvResponseDto;

import java.util.List;

public interface AdvService {
    List<AdvResponseDto> findAll();
    AdvResponseDto createAdv(AdvRequestDto requestDto);
    AdvResponseDto update(UpdateAdvRequestDto dto);
    AdvResponseDto delAdv(Long id);
}
