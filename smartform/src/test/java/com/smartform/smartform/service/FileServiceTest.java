package com.smartform.smartform.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    FileService fileService = new FileService();

    @TempDir
    Path tempDir;

    @Test
    void testDownloadFile_createsCorrectPath() throws IOException {
        ReflectionTestUtils.setField(fileService, "downloadUrl", "https://www.smartform.cz/download/");

        String obec = "kopidlno";
        Path targetPath = fileService.downloadFile(obec, tempDir.toString());

        assertTrue(targetPath.toString().endsWith(".xml.zip"));
        assertTrue(targetPath.startsWith(tempDir));
    }

    @Test
    void testUnzipFile_unzipsFile(@TempDir Path tempDir) throws IOException {

        Path zipFile = tempDir.resolve("test.zip");
        Path xmlFileName = Path.of("test.xml");
        String xmlContent = "<root>test</root>";

        try (var zos = new java.util.zip.ZipOutputStream(java.nio.file.Files.newOutputStream(zipFile))) {
            var entry = new java.util.zip.ZipEntry(xmlFileName.toString());
            zos.putNextEntry(entry);
            zos.write(xmlContent.getBytes());
            zos.closeEntry();
        }

        Path destDir = tempDir.resolve("unzip");
        java.nio.file.Files.createDirectories(destDir);

        Path unzippedFilePath = fileService.unzipFile(zipFile, destDir.toString());

        assertNotNull(unzippedFilePath);
        assertTrue(unzippedFilePath.toString().endsWith(".xml"));
        assertTrue(java.nio.file.Files.exists(unzippedFilePath));
    }

    @Test
    void testUnzipFile_throwsExceptionIfEntryIsOutside(@TempDir Path tempDir) throws IOException {
        Path zipFile = tempDir.resolve("bad.zip");

        try (var zos = new java.util.zip.ZipOutputStream(java.nio.file.Files.newOutputStream(zipFile))) {
            var badEntry = new java.util.zip.ZipEntry("../evil.txt");
            zos.putNextEntry(badEntry);
            zos.write("bad".getBytes());
            zos.closeEntry();
        }

        Path destDir = tempDir.resolve("unzip");
        java.nio.file.Files.createDirectories(destDir);

        IOException ex = assertThrows(IOException.class, () -> {
            fileService.unzipFile(zipFile, destDir.toString());
        });

        assertTrue(ex.getMessage().contains("Entry is outside of the target dir"));
    }
}
