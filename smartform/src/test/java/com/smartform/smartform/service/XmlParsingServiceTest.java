package com.smartform.smartform.service;

import com.smartform.smartform.xml.VymennyFormatXml;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XmlParsingServiceTest {

    private final XmlParsingService xmlParsingService = new XmlParsingService();

    @Test
    void parseXml_validFile_shouldReturnObject() throws JAXBException, SAXException {
        // Arrange: path to sample XML in test resources
        Path xmlPath = Paths.get("src/test/resources/sample.xml");

        // Act
        VymennyFormatXml result = xmlParsingService.parseXml(xmlPath);

        // Assert
        assertNotNull(result, "Parsed XML object should not be null");

        // Optionally assert specific fields in 'result' if you want
        // e.g., assertEquals("ExpectedValue", result.getSomeField());
    }

    @Test
    void parseXml_nonExistingFile_shouldThrowException() {
        // Arrange
        Path xmlPath = Paths.get("src/test/resources/nonexistent.xml");

        // Act & Assert
        assertThrows(Exception.class, () -> {
            xmlParsingService.parseXml(xmlPath);
        });
    }
}
