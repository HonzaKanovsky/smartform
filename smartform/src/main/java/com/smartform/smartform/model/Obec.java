package com.smartform.smartform.model;

import com.smartform.smartform.dto.ObecDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Entity
@Data
public class Obec {
    @Id
    private Integer kod;

    private String nazev;

    @OneToMany(mappedBy = "obec")
    private List<CastObce> castiObce;


    public ObecDto toDto() {
        return new ObecDto(this.kod, this.nazev,
                this.castiObce == null ? Collections.emptyList() : this.castiObce.stream().map(CastObce::toDto).toList());
    }
}
