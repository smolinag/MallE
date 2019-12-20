/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers.hbridge;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Santiago Noatec
 */
public enum Speed {
    STOP(0, "STOP"),
    SLOW(30, "SLOW"),
    MEDIUM(50, "MEDIUM"),
    FAST(70, "FAST"),
    MAX(85, "MAX");
    
    private int pwmVal;
    private String speedName;
    private static Map<String, Speed> mapName = new HashMap<String, Speed>();

    static {
        for (Speed speed : Speed.values()) {
            mapName.put(speed.speedName, speed);
        }
    }
    
    Speed(int pwmVal, String speedName){
        this.pwmVal = pwmVal;
        this.speedName = speedName;
    }
    
    public int getPwmVal(){
        return pwmVal;
    }
    
    public static Speed getSpeedLevelByName(String speedName) {
        return mapName.get(speedName);
    }
}
