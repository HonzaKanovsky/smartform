package com.smartform.smartform.service;

import com.smartform.smartform.Repository.CastObceRepository;
import com.smartform.smartform.Repository.ObecRepository;
import com.smartform.smartform.dto.ObecDto;
import com.smartform.smartform.dto.ObecResponseDto;
import com.smartform.smartform.model.CastObce;
import com.smartform.smartform.model.Obec;
import com.smartform.smartform.xml.CastObceXml;
import com.smartform.smartform.xml.ObecXml;
import com.smartform.smartform.xml.VymennyFormatXml;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class ObecService {

    @Autowired
    private CastObceRepository castObceRepository;
    @Autowired
    private ObecRepository obecRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private XmlParsingService xmlParsingService;


    public ObecResponseDto updateObecData(String obecName) {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path zipPath = null;
        Path xmlPath = null;

        try {
            zipPath = fileService.downloadFile(obecName, tempDir);
            xmlPath = fileService.unzipFile(zipPath, tempDir);
            VymennyFormatXml vf = xmlParsingService.parseXml(xmlPath);


            return new ObecResponseDto(saveObecXmlToDb(vf));

        } catch (IOException | JAXBException | SAXException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (xmlPath != null)
                    Files.deleteIfExists(xmlPath);
                if (zipPath != null)
                    Files.deleteIfExists(zipPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private List<ObecDto> saveObecXmlToDb(VymennyFormatXml vf) {
        List<Obec> obceToBeSaved = new ArrayList<>();

        //Save all Obce first
        for (ObecXml obecXml : vf.getData().getObceXml().getObecList()) {
            Obec newObec = new Obec();
            newObec.setKod(obecXml.getKod());
            newObec.setNazev(obecXml.getNazev());

            obceToBeSaved.add(newObec);
        }
        obecRepository.saveAll(obceToBeSaved);

        //Save Casti for all obce
        for (Obec obec : obceToBeSaved) {
            List<CastObceXml> castiProObec = vf.getData().getCastiObciXml().getCastObceList().stream()
                    .filter(castObce ->
                            (castObce.getObecRef() != null) && (obec.getKod().equals(castObce.getObecRef().getKod())))
                    .toList();

            List<CastObce> castiObceToBeSaved = new ArrayList<>();

            for (CastObceXml castObceXml : castiProObec) {
                CastObce castObce = new CastObce();
                castObce.setKod(castObceXml.getKod());
                castObce.setNazev(castObceXml.getNazev());
                castObce.setObec(obec);

                castiObceToBeSaved.add(castObce);
            }

            obec.setCastiObce(castiObceToBeSaved);
            castObceRepository.saveAll(castiObceToBeSaved);
        }

        return obceToBeSaved.stream().map(Obec::toDto).toList();
    }


    public ObecResponseDto getObceWithCasti() {
        return new ObecResponseDto(obecRepository.findAll().stream().map(Obec::toDto).toList());
    }
}
