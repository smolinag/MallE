/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.dto;

/**
 *
 * @author smoli
 */
public class UltrasoundMeasurementDto {
    
    private String name;
    private Double value;

    public UltrasoundMeasurementDto(String name, Double value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String usName) {
        this.name = usName;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }    
}
