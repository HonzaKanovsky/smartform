package com.smartform.smartform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ObecResponseDto {
    List<ObecDto> obecDtoList = new ArrayList<>();

    public ObecResponseDto(List<ObecDto> obecDtoList) {
        this.obecDtoList = obecDtoList;
    }
}
