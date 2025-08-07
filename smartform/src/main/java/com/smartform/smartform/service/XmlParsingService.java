package com.smartform.smartform.service;

import com.smartform.smartform.xml.VymennyFormatXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.File;
import java.nio.file.Path;

@Service
public class XmlParsingService {

    VymennyFormatXml parseXml(Path xmlPath) throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(VymennyFormatXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //TODO Validate schema

        File xmlFile = xmlPath.toFile();
        return (VymennyFormatXml) unmarshaller.unmarshal(xmlFile);
    }
}
