package com.smartform.smartform.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ObceXml {

    @XmlElement(name = "Obec", namespace = "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1")
    private List<ObecXml> obecList;

}