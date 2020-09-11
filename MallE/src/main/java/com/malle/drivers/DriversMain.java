/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers;

import com.malle.drivers.hbridge.HBridgeDriver;
import com.malle.drivers.servomotor.ServoMotorDriver;
import com.malle.drivers.ultrasound.UltrasoundDriver;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Santiago Noatec
 */
public class DriversMain {

    private static ServoMotorDriver verticalServo = null;
    private static ServoMotorDriver horizontalServo = null;
    private static UltrasoundDriver lowerUltrasound = null;
    private static UltrasoundDriver frontUltrasound = null;
    private static UltrasoundDriver headUltrasound = null;
    private static HBridgeDriver hBridgeDriver = null;

    public static void initializeDrivers() {
        //Servos
        verticalServo = new ServoMotorDriver("VERTICAL", RaspiPin.GPIO_26, -90, 90, 0);
        horizontalServo = new ServoMotorDriver("HORIZONTAL", RaspiPin.GPIO_23, -90, 90, 0);

        //Ultrasound
        lowerUltrasound = new UltrasoundDriver("LOWER", RaspiPin.GPIO_10, RaspiPin.GPIO_06, 0);
        frontUltrasound = new UltrasoundDriver("FRONT", RaspiPin.GPIO_05, RaspiPin.GPIO_04, 1);
        headUltrasound = new UltrasoundDriver("HEAD", RaspiPin.GPIO_29, RaspiPin.GPIO_28, 1);

        //Hbridge
        hBridgeDriver = new HBridgeDriver(RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_07, RaspiPin.GPIO_00);        
    }

    public static ServoMotorDriver getVerticalServo() {
        return verticalServo;
    }

    public static ServoMotorDriver getHorizontalServo() {
        return horizontalServo;
    }

    public static UltrasoundDriver getLowerUltrasound() {
        return lowerUltrasound;
    }
    
    public static UltrasoundDriver getFrontUltrasound() {
        return frontUltrasound;
    }
    
    public static UltrasoundDriver getHeadUltrasound() {
        return headUltrasound;
    }

    public static HBridgeDriver getHBridgeDriver() {
        return hBridgeDriver;
    }
}
