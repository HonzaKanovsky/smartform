package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Obec", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
public class ObecXml {
    @XmlElement(name = "Kod", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
    private Integer kod;

    @XmlElement(name = "Nazev", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
    private String nazev;
}