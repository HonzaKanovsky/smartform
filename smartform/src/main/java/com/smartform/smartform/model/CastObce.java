package com.smartform.smartform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CastObce {
    @Id
    private String kod;

    private String nazev;

    @ManyToOne
    @JoinColumn(name = "obec_kod", referencedColumnName = "kod")
    private Obec obec;
}