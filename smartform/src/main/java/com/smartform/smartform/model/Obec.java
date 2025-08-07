package com.smartform.smartform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Obec {
    @Id
    private String kod;

    private String nazev;

    @OneToMany(mappedBy = "obec")
    private List<CastObce> castiObce;
}
