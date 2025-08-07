package com.smartform.smartform.service;

import com.smartform.smartform.Repository.CastObceRepository;
import com.smartform.smartform.Repository.ObecRepository;
import com.smartform.smartform.dto.ObecDto;
import com.smartform.smartform.dto.ObecResponseDto;
import com.smartform.smartform.model.Obec;
import com.smartform.smartform.xml.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObecServiceTest {

    @InjectMocks
    private ObecService obecService;

    @Mock
    private FileService fileService;

    @Mock
    private XmlParsingService xmlParsingService;

    @Mock
    private ObecRepository obecRepository;

    @Mock
    private CastObceRepository castObceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateObecData_success() throws Exception {
        String obecName = "kopidlno";
        String tempDir = System.getProperty("java.io.tmpdir");
        Path fakeZipPath = Path.of(tempDir, "kopidlno.xml.zip");
        Path fakeXmlPath = Path.of(tempDir, "kopidlno.xml");

        // Mock fileService methods
        when(fileService.downloadFile(eq(obecName), eq(tempDir))).thenReturn(fakeZipPath);
        when(fileService.unzipFile(eq(fakeZipPath), eq(tempDir))).thenReturn(fakeXmlPath);

        // Prepare fake VymennyFormatXml with one Obec and its CastObce
        VymennyFormatXml vf = mock(VymennyFormatXml.class);
        DataXml data = mock(DataXml.class);
        ObceXml obceXml = mock(ObceXml.class);
        CastiObciXml castiObciXml = mock(CastiObciXml.class);

        ObecXml obecXml = new ObecXml();
        obecXml.setKod("123");
        obecXml.setNazev("TestObec");

        CastObceXml castObceXml = new CastObceXml();
        castObceXml.setKod("321");
        castObceXml.setNazev("CastTest");
        // Link castObce to obec
        ObecRef obecRef = new ObecRef();
        obecRef.setKod("123");
        castObceXml.setObecRef(obecRef);

        // Setup lists
        List<ObecXml> obecList = List.of(obecXml);
        List<CastObceXml> castObceList = List.of(castObceXml);

        when(vf.getData()).thenReturn(data);
        when(data.getObceXml()).thenReturn(obceXml);
        when(data.getCastiObciXml()).thenReturn(castiObciXml);
        when(obceXml.getObecList()).thenReturn(obecList);
        when(castiObciXml.getCastObceList()).thenReturn(castObceList);

        when(xmlParsingService.parseXml(fakeXmlPath)).thenReturn(vf);

        // Mock repository saveAll behavior to return the entities passed in
        when(obecRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));
        when(castObceRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Call service method
        ObecResponseDto responseDto = obecService.updateObecData(obecName);

        // Assertions
        assertNotNull(responseDto);
        assertEquals(1, responseDto.getObecDtoList().size());

        ObecDto savedObec = responseDto.getObecDtoList().get(0);
        assertEquals("123", savedObec.getKod());
        assertEquals("TestObec", savedObec.getNazev());
        assertEquals(1, savedObec.getCastiObce().size());
        assertEquals("321", savedObec.getCastiObce().get(0).getKod());
        assertEquals("CastTest", savedObec.getCastiObce().get(0).getNazev());

        // Verify mocks were called
        verify(fileService).downloadFile(eq(obecName), eq(tempDir));
        verify(fileService).unzipFile(eq(fakeZipPath), eq(tempDir));
        verify(xmlParsingService).parseXml(eq(fakeXmlPath));
        verify(obecRepository).saveAll(anyList());
        verify(castObceRepository).saveAll(anyList());
    }

    @Test
    void updateObecData_throwsRuntimeException_onIOException() throws Exception {
        String obecName = "kopidlno";
        String tempDir = System.getProperty("java.io.tmpdir");

        when(fileService.downloadFile(eq(obecName), eq(tempDir))).thenThrow(new java.io.IOException("Failed download"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            obecService.updateObecData(obecName);
        });

        assertTrue(ex.getCause() instanceof java.io.IOException);
        assertEquals("Failed download", ex.getCause().getMessage());
    }

    @Test
    void getObceWithCasti_returnsList() {
        List<Obec> obce = new ArrayList<>();
        Obec obec = new Obec();
        obec.setKod("999");
        obec.setNazev("MockObec");
        obce.add(obec);

        when(obecRepository.findAll()).thenReturn(obce);

        ObecResponseDto response = obecService.getObceWithCasti();

        assertNotNull(response);
        assertEquals(1, response.getObecDtoList().size());
        assertEquals("999", response.getObecDtoList().get(0).getKod());
        assertEquals("MockObec", response.getObecDtoList().get(0).getNazev());

        verify(obecRepository).findAll();
    }
}
