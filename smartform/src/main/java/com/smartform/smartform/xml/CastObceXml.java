package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CastObceXml {
    @XmlElement(name = "Kod", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private String kod;

    @XmlElement(name = "Nazev", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private String nazev;

    @XmlElement(name = "Obec", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private ObecRef obecRef;
}

