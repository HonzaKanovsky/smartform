package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Obec", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
public class ObecXml {
    @XmlElement(name = "Kod", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
    private String kod;

    @XmlElement(name = "Nazev", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
    private String nazev;
}