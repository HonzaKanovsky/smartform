package com.smartform.smartform.controller;

import com.smartform.smartform.dto.ObecResponseDto;
import com.smartform.smartform.service.ObecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/obec")
public class ObecController {

    @Autowired
    private ObecService obecService;


    @GetMapping
    public ObecResponseDto getAllObce() {
        return obecService.getObceWithCasti();
    }

    @PostMapping
    public ObecResponseDto initiateObecSaving(
            @RequestParam(name = "obec", defaultValue = "kopidlno") String obec) {
        if (!StringUtils.hasText(obec))
            obec = "kopidlno";
        return obecService.updateObecData(obec);
    }
}
