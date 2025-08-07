package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@Data
@XmlRootElement(name = "VymennyFormat", namespace = "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1")
@XmlAccessorType(XmlAccessType.FIELD)
public class VymennyFormatXml {

    @XmlElement(name = "Data", namespace = "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1")
    private DataXml data;

}