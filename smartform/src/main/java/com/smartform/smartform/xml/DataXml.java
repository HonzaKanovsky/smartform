package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DataXml {

    @XmlElement(name = "Obce", namespace = "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1")
    private ObceXml obceXml;

    @XmlElement(name = "CastiObci", namespace = "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1")
    private CastiObciXml castiObciXml;

}