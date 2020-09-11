/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.services;

import com.malle.drivers.DriversMain;
import com.malle.dto.UltrasoundMeasurementDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author smoli
 */
@Service
public class SensorsService {
    
    public ResponseEntity<List<UltrasoundMeasurementDto>> queryUltrasoundSensors() {
        
        List<UltrasoundMeasurementDto> measurements = new ArrayList<>();
        
        measurements.add(new UltrasoundMeasurementDto(DriversMain.getLowerUltrasound().getName(), DriversMain.getLowerUltrasound().getLatestMeasurement()));
        measurements.add(new UltrasoundMeasurementDto(DriversMain.getFrontUltrasound().getName(), DriversMain.getFrontUltrasound().getLatestMeasurement()));
        measurements.add(new UltrasoundMeasurementDto(DriversMain.getHeadUltrasound().getName(), DriversMain.getHeadUltrasound().getLatestMeasurement()));
        return ResponseEntity.status(HttpStatus.OK).body(measurements);
    }
}
