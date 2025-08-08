package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CastObceXml {
    @XmlElement(name = "Kod", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private Integer kod;

    @XmlElement(name = "Nazev", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private String nazev;

    @XmlElement(name = "Obec", namespace = "urn:cz:isvs:ruian:schemas:CastObceIntTypy:v1")
    private ObecRef obecRef;
}

