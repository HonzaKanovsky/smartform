package com.smartform.smartform.controller;

import com.smartform.smartform.dto.ObecResponseDto;
import com.smartform.smartform.service.ObecService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ObecControllerTest {

    @InjectMocks
    private ObecController obecController;

    @Mock
    private ObecService obecService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllObce_Success() {
        // Arrange
        ObecResponseDto fakeResponse = new ObecResponseDto();
        // populate fakeResponse with dummy data if needed

        when(obecService.getObceWithCasti()).thenReturn(fakeResponse);

        // Act
        ObecResponseDto result = obecController.getAllObce();

        // Assert
        assertNotNull(result);
        assertEquals(fakeResponse, result);
        verify(obecService, times(1)).getObceWithCasti();
    }

    @Test
    public void testGetAllObce_ServiceThrowsException() {
        // Arrange
        when(obecService.getObceWithCasti()).thenThrow(new RuntimeException("Service failure"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            obecController.getAllObce();
        });

        assertEquals("Service failure", ex.getMessage());
        verify(obecService, times(1)).getObceWithCasti();
    }

    @Test
    public void testInitiateObecSaving_Success() {
        // Arrange
        String obecName = "kopidlno";
        ObecResponseDto fakeResponse = new ObecResponseDto();
        // populate fakeResponse if needed

        when(obecService.updateObecData(obecName)).thenReturn(fakeResponse);

        // Act
        ObecResponseDto result = obecController.initiateObecSaving(obecName);

        // Assert
        assertNotNull(result);
        assertEquals(fakeResponse, result);
        verify(obecService, times(1)).updateObecData(obecName);
    }

    @Test
    public void testInitiateObecSaving_DefaultValueUsed() {
        // Arrange
        String defaultObecName = "kopidlno";
        ObecResponseDto fakeResponse = new ObecResponseDto();

        when(obecService.updateObecData(defaultObecName)).thenReturn(fakeResponse);

        // Act
        ObecResponseDto result = obecController.initiateObecSaving("");

        // Assert
        assertNotNull(result);
        assertEquals(fakeResponse, result);
        verify(obecService, times(1)).updateObecData(defaultObecName);
    }

    @Test
    public void testInitiateObecSaving_ServiceThrowsException() {
        // Arrange
        String obecName = "nonexistent";
        when(obecService.updateObecData(obecName)).thenThrow(new IllegalArgumentException("Obec not found"));

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            obecController.initiateObecSaving(obecName);
        });

        assertEquals("Obec not found", ex.getMessage());
        verify(obecService, times(1)).updateObecData(obecName);
    }
}
