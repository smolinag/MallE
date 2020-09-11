/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.controllers;

import com.malle.dto.UltrasoundMeasurementDto;
import com.malle.services.SensorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smoli
 */
@RestController
@CrossOrigin
@RequestMapping("${datasource.app.uri}/sensors")
public class SensorsController {   
    
    @Autowired
    private SensorsService sensorsService;
    
    @RequestMapping(value= "/ultrasound", method = RequestMethod.GET)
    public ResponseEntity<List<UltrasoundMeasurementDto>> moveHeadServo() {
        return sensorsService.queryUltrasoundSensors();
    }
}
