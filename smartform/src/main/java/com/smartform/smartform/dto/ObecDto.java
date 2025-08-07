package com.smartform.smartform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ObecDto {
    private String kod;
    private String nazev;
    private List<CastObceDto> castiObce;
}
